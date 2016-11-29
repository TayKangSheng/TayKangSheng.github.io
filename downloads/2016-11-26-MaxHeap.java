
import java.util.ArrayList;


public class MaxHeap {
	private ArrayList<Integer> heap;
	
	public static void main(String args[]){
		int[] test = {4,1,3,2,16,9,10,14,8,7};
		MaxHeap heap = new MaxHeap();
		System.out.println("---build_max_heap(test)---");
		heap.build_max_heap(test);
		System.out.println("results: "+heap);
		System.out.println("---insert(17)---");
		heap.insert(17);
		System.out.println("results: "+heap);
		System.out.println("---extract_max()---");
		heap.extract_max();
		System.out.println("results: "+heap);
		System.out.println("---heapsort()---");
		int[] sorted = heap.heapsort();
		System.out.print("results: [");
		for (int i=0 ; i<sorted.length-1 ; i++){
			System.out.print(sorted[i]+", ");
		}
		System.out.println(sorted[sorted.length-1]+"]");
	}
	
	public MaxHeap(){
		heap = new ArrayList<Integer>();
	}
	
	public void max_heapify(int index){
		int left_index = (index*2);
		int right_index = (index*2)+1;
		int largest_index;
		if (left_index < heap.size() && heap.get(left_index)>heap.get(index) ){
			largest_index = left_index;
		} else {
			largest_index = index;
		}
		
		if (right_index < heap.size() && heap.get(right_index)>heap.get(largest_index) ){
			largest_index = right_index;
		}
		
		if (largest_index != index){
			int temp = heap.get(largest_index);
			heap.set(largest_index, heap.get(index));
			heap.set(index, temp);
			max_heapify(largest_index);
		}
	}
	
	public void build_max_heap(int[] array){
		heap.add(null); // This is to ensure that the index of the first element is 1;
		for (int i=0 ; i<array.length ; i++){
			heap.add(array[i]);
		}
		for (int i=heap.size()/2; i>0 ; i--){
			max_heapify(i);
		}
	}
	
	public void insert(int value){
		heap.add(value);
		int current_index = heap.size()-1;
		while (current_index>1){
			int parent_index = current_index/2;
			if ( heap.get(parent_index) < heap.get(current_index) ){
				int temp = heap.get(parent_index);
				heap.set(parent_index, heap.get(current_index));
				heap.set(current_index, temp);
				current_index = parent_index;
			} else {
				break;
			}
		}
	}
	
	public int extract_max(){
		if (heap.size() <= 2){
			return heap.remove(1);
		} else {
			int max = heap.get(1);
			int last_node = heap.get(heap.size()-1);
			heap.remove(heap.size()-1);
			heap.set(1, last_node);
			max_heapify(1);
			return max;
		}
	}
	
	public int[] heapsort(){
		int[] sorted_array = new int[heap.size()-1];
		for (int i=0 ; i<sorted_array.length ; i++){
			sorted_array[i] = extract_max();
		}
		return sorted_array;
	}
}
