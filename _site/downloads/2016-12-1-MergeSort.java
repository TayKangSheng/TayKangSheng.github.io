import java.util.Comparator;
import java.util.Random;

public class MergeSort<T extends Comparable>{
	public static void main(String[] args){
		Random rand = new Random();
		Integer[] a = new Integer[999];
		for (int i=0 ; i<a.length ; i++){
			a[i] = rand.nextInt(a.length*10) + 1;
		}
		
		MergeSort<Integer> mergeSort = new MergeSort<Integer>(a);
		mergeSort.sort();
		System.out.println(mergeSort);
	}
	
	/************************************
	 * 
	 * 		Variables
	 * 
	 ************************************/
	T[] array;
	Comparator<T> comparator;
	
	/************************************
	 * 
	 * 		Constructors
	 * 
	 ************************************/
	public MergeSort(T[] toSort){
		array = toSort;
		comparator = null;
	}
	public MergeSort(T[] toSort, Comparator<T> comp){
		array = toSort;
		comparator = comp;
	}
	
	/************************************
	 * 
	 * 		Helper Methods
	 * 
	 ************************************/
	private int compare(T x, T y){
		if (comparator == null){
			return x.compareTo(y);
		} else {
			return comparator.compare(x, y);
		}
	}
	
	public String toString(){
		String toPrint = "[";
		for (int i=0 ; i<array.length-1 ; i++){
			toPrint += array[i];
			toPrint += ", ";
		}
		toPrint += array[array.length-1];
		toPrint += "]";
		return toPrint;
	}
	
	private T[][] divide(T[] toDivide){
		T[] toSort1 = (T[]) new Comparable[toDivide.length/2];
		T[] toSort2 = (T[]) new Comparable[toDivide.length - toSort1.length];
		for (int i=0 ; i<toSort1.length ; i++){
			toSort1[i] = toDivide[i];
		}
		for (int i=0 ; i<toSort2.length ; i++){
			toSort2[i] = toDivide[toSort1.length+i];
		}
		
		T[][] divided = (T[][]) new Comparable[2][];
		divided[0] = toSort1;
		divided[1] = toSort2;
		return divided;
	}
	
	private T[] merge(T[] toMerge1, T[] toMerge2){
		T[] toSort1 = toMerge1;
		T[] toSort2 = toMerge2;
		T[] toMerge = (T[]) new Comparable[toMerge1.length+toMerge2.length];
		
		int[] indexes = {0, 0};
		for (int i=0 ; i<toMerge.length ; i++){
			if ( indexes[0]>toSort1.length-1 ){
				// case 2: not valid, valid
				toMerge[i] = toSort2[indexes[1]];
				indexes[1] = indexes[1]+1;
			} else if ( indexes[1]>toSort2.length-1 ){
				// case 3: valid, not valid
				toMerge[i] = toSort1[indexes[0]];
				indexes[0] = indexes[0]+1;
			} else{
				if ( compare(toSort1[indexes[0]], toSort2[indexes[1]]) < 0){
					toMerge[i] = toSort1[indexes[0]];
					indexes[0] = indexes[0]+1;
				} else {
					toMerge[i] = toSort2[indexes[1]];
					indexes[1] = indexes[1]+1;
				}
			}
		}
		return toMerge;
	}
	
	/************************************
	 * 
	 * 		Methods
	 * 
	 ************************************/
	public T[] sort(){
		array = sort(array);
		return array;
	}
	private T[] sort(T[] toSort){
		if (toSort.length < 2){
			// base case
			return toSort;
		}
		
		T[][] divide = divide(toSort);
		divide[0] = sort(divide[0]); 
		divide[1] = sort(divide[1]);		
		toSort = merge(divide[0], divide[1]);
		
		return toSort;
	}
}
