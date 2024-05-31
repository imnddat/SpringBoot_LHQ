public class App {
    // Bài toán: phát triển 1 tính năng phức tạp nhất thế giới
    public static void main(String[] args) throws Exception {
        VeryComplexService service = new VeryComplexService();
        int [] numbers = new int[]{1,2,3,4,5};
        service.complexFunction(numbers);
    }
}
