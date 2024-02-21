public class QuickSort <T extends Comparable<? super T>> extends Thread{
    private T[] vet;
    private int inicio;
    private int fim;
    
    public QuickSort(T vet[], int inicio, int fim) {
        this.vet = vet;
        this.inicio = inicio;
        this.fim = fim;
    }

    @Override
    public void run() {
        quickSort();
    }
    
    public void quickSort() {
        if (inicio < fim) {
            int posPivo = particao(vet, inicio, fim);
    
            QuickSort<T> threadEsq = new QuickSort<>(vet, inicio, posPivo - 1);
            QuickSort<T> threadDir = new QuickSort<>(vet, posPivo + 1, fim);

            threadEsq.start();
            threadDir.start();

            try {
                threadEsq.join();
                threadDir.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int particao(T vet[], int inicio, int fim) {
        T pivo = vet[fim];
        int i = (inicio-1);
    
        for (int j = inicio; j < fim; j++) {
            if (vet[j].compareTo(pivo) <= 0) {
                i++;
    
                T temp = vet[i];
                vet[i] = vet[j];
                vet[j] = temp;
            }
        }
    
        T swapTemp = vet[i+1];
        vet[i+1] = vet[fim];
        vet[fim] = swapTemp;
    
        return i+1;
    }
}
