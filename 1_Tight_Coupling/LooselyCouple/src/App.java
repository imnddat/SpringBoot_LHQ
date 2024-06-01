public class App {
    public static void main(String[] args) throws Exception {
        BubbleSort bubbleSort = new BubbleSort();
        VeryComplexService service = new VeryComplexService(bubbleSort);
        int[] numbers = new int[]{1,5,6};
        service.complexFunction(numbers);
    }
}
