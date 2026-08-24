class Result<T> {
    private final boolean success;
    private final String message;
    private final T data;

    Result(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    boolean isSuccess() {
        return success;
    }

    String getMessage() {
        return message;
    }

    T getData() {
        return data;
    }
}

public class GenericResultDemo {
    public static void main(String[] args) {
        Result<String> successResult =
                new Result<>(true, "取得姓名成功", "Amy");

        Result<Integer> scoreResult =
                new Result<>(true, "取得成績成功", 95);

        Result<String> failedResult =
                new Result<>(false, "查詢失敗", null);

        System.out.println(
                "success=" + successResult.isSuccess());

        System.out.println(
                "message=" + successResult.getMessage());

        System.out.println(
                "data=" + successResult.getData());

        System.out.println(
                "score=" + scoreResult.getData());

        System.out.println(
                "failed success=" + failedResult.isSuccess());

        System.out.println(
                "failed message=" + failedResult.getMessage());

        System.out.println(
                "failed data=" + failedResult.getData());
    }
}