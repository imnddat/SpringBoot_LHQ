public class VeryComplexService {
    BubbleSort bubbleSort = new BubbleSort();

    QuickSort quickSort = new QuickSort();
    // Hàm xử lý rất phức tạp
    //  Một trong số đó là việc sắp xếp dữ liệu đầu vào
    public void complexFunction(int[] numbers){
        bubbleSort.sort(numbers);

        quickSort.sapxep(numbers);
        // Xử lý các logic phức tạp
    }
}
