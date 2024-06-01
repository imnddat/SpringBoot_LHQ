public class VeryComplexService {
    private ISortAlgorithm sortAlgorithm;

    public VeryComplexService(ISortAlgorithm sortAlgorithm){
        this.sortAlgorithm = sortAlgorithm;
    }

    public void complexFunction(int[] numbers){
        sortAlgorithm.sort(numbers);
    }
}
