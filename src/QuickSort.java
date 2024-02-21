public class QuickSort <T extends Comparable<? super T>> extends Thread{
    private T[] vet;
    private int inicio;
    private int fim;
    
    //construtor da classe para a inicializacao de objetos
    public QuickSort(T vet[], int inicio, int fim) {
        this.vet = vet;
        this.inicio = inicio;
        this.fim = fim;
    }

    //metodo para definir o comportamento da thread
    @Override
    public void run() {
        quickSort();
    }
    
    //ordena de acordo com o QuickSort(dividir para conquistar)
    public void quickSort() {
        if (inicio < fim) {
            int posPivo = particao(vet, inicio, fim); //encontra a posicao do pivo para poder "dividir" o vetor
    
            QuickSort<T> threadEsq = new QuickSort<>(vet, inicio, posPivo - 1);//um novo objeto que sera responsavel por executar, atraves de uma thread, o lado esquerdo do vetor que foi partido
            QuickSort<T> threadDir = new QuickSort<>(vet, posPivo + 1, fim);//um novo objeto que sera responsavel por executar, atraves de uma thread, o lado direito do vetor que foi partido

            threadEsq.start();//inicia a thread da esquerda
            threadDir.start();//inicia a thread da direita

            try {
                //aguarda a conclusao das duas threads
                threadEsq.join();
                threadDir.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //faz a particao do vetor
    private int particao(T vet[], int inicio, int fim) {
        T pivo = vet[fim];//pivo como ultimo elemento do vetor
        int i = (inicio-1);//pega o indice do menor elemento do vetor
    
        for (int j = inicio; j < fim; j++) {//percorre o vetor
            //verifica se o elemento atual eh menor ou igual ao e pivo e, caso seja, sera feita a troca
            if (vet[j].compareTo(pivo) <= 0) {
                i++;
    
                T temp = vet[i];
                vet[i] = vet[j];
                vet[j] = temp;
            }
        }
    
        //coloca o pivo na posicao correta
        T temp = vet[i+1];
        vet[i+1] = vet[fim];
        vet[fim] = temp;
    
        return i+1;//retorna a posicao do pivo
    }
}
