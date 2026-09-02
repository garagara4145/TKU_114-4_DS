import java.util.*;

public class WebsiteLinkGraph {

    private final Map<String, Set<String>> outgoing =
        new LinkedHashMap<>();

    public void addWebsite(String website) {

        if (website == null || website.isBlank()) {
            throw new IllegalArgumentException("website");
        }

        outgoing.putIfAbsent(website.trim(), new LinkedHashSet<>());
    }

    public boolean addLink(String from, String to) {

        checkWebsite(from);
        checkWebsite(to);

        Set<String> links = outgoing.get(from);

        if (links.contains(to)) {
            return false;
        }

        links.add(to);

        return true;
    }

    public boolean removeLink(String from, String to) {

        checkWebsite(from);
        checkWebsite(to);

        return outgoing.get(from).remove(to);
    }

    public Set<String> outgoingLinks(String website) {

        checkWebsite(website);

        return new LinkedHashSet<>(
            outgoing.get(website)
        );
    }

    public int outDegree(String website) {

        checkWebsite(website);

        return outgoing.get(website).size();
    }

    public int inDegree(String website) {

        checkWebsite(website);

        int count = 0;

        for (Set<String> links : outgoing.values()) {

            if (links.contains(website)) {
                count++;
            }
        }

        return count;
    }

    public Set<String> incomingLinks(String website) {

        checkWebsite(website);

        Set<String> result =
            new LinkedHashSet<>();

        for (String from : outgoing.keySet()) {

            if (outgoing.get(from).contains(website)) {
                result.add(from);
            }
        }

        return result;
    }

    public List<String> noIncomingPages() {

        List<String> result =
            new ArrayList<>();

        for (String website : outgoing.keySet()) {

            if (inDegree(website) == 0) {
                result.add(website);
            }
        }

        return result;
    }

    public List<String> noOutgoingPages() {

        List<String> result =
            new ArrayList<>();

        for (String website : outgoing.keySet()) {

            if (outDegree(website) == 0) {
                result.add(website);
            }
        }

        return result;
    }

    private void checkWebsite(String website) {

        if (website == null || !outgoing.containsKey(website)) {
            throw new IllegalArgumentException(
                "Unknown website: " + website
            );
        }
    }

    public void printGraph() {

        System.out.println("--- Website Link Graph ---");

        for (String website : outgoing.keySet()) {

            System.out.println(
                website + " -> " + outgoing.get(website)
            );
        }
    }

    public static void main(String[] args) {

        WebsiteLinkGraph graph =
            new WebsiteLinkGraph();

        graph.addWebsite("Google");
        graph.addWebsite("YouTube");
        graph.addWebsite("Wikipedia");
        graph.addWebsite("GitHub");
        graph.addWebsite("Blog");

        System.out.println(
            "Google -> YouTube: "
            + graph.addLink("Google", "YouTube")
        );

        System.out.println(
            "Google -> Wikipedia: "
            + graph.addLink("Google", "Wikipedia")
        );

        System.out.println(
            "Wikipedia -> Google: "
            + graph.addLink("Wikipedia", "Google")
        );

        System.out.println(
            "GitHub -> Wikipedia: "
            + graph.addLink("GitHub", "Wikipedia")
        );

        System.out.println(
            "Google -> YouTube again: "
            + graph.addLink("Google", "YouTube")
        );

        System.out.println();

        graph.printGraph();

        System.out.println();

        System.out.println(
            "Google outgoing: "
            + graph.outgoingLinks("Google")
        );

        System.out.println(
            "Wikipedia incoming: "
            + graph.incomingLinks("Wikipedia")
        );

        System.out.println(
            "Wikipedia in-degree: "
            + graph.inDegree("Wikipedia")
        );

        System.out.println(
            "Google out-degree: "
            + graph.outDegree("Google")
        );

        System.out.println();

        System.out.println(
            "No incoming pages: "
            + graph.noIncomingPages()
        );

        System.out.println(
            "No outgoing pages: "
            + graph.noOutgoingPages()
        );

        System.out.println();

        System.out.println(
            "Remove Google -> Wikipedia: "
            + graph.removeLink("Google", "Wikipedia")
        );

        System.out.println(
            "Google outgoing: "
            + graph.outgoingLinks("Google")
        );
    }
}