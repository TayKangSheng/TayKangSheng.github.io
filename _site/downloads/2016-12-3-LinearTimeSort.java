import java.util.Random;

/***********************************************
 * 
 * 		Counting Sort (aka. Bucket Sort)
 * 		Radix Sort (LSD, MSD)
 * 
 ***********************************************/
public class LinearTimeSort {
	public static void main(String[] args){
		Random rand = new Random();
		int[] a = new int[100];
		for (int i=0 ; i<a.length ; i++){
			a[i] = rand.nextInt(a.length);
		}
		LinearTimeSort sorter = new LinearTimeSort();
		sorter.printArray(a);
		sorter.printArray(sorter.countingSort(a));
		sorter.printArray(sorter.radixSort_LSD(a));
		sorter.printArray(sorter.radixSort_MSD(a));
	}
	
	/********************************************
	 * 
	 * 		Constructor
	 * 
	 ********************************************/
	public LinearTimeSort(){  }
	
	/********************************************
	 * 
	 * 		Helper Methods
	 * 
	 ********************************************/
	public void printArray(int[] toPrint){
		System.out.print("[ ");
		for (int i=0 ; i<toPrint.length-1 ; i++){
			System.out.print(toPrint[i]+", ");
		}
		System.out.println(toPrint[toPrint.length-1]+"]");
	}
	
	public int getNthDigit(int number, int base, int n) {    
	  return (int) ((number / Math.pow(base, n - 1)) % base);
	}
	
	private int[] concat(int[] x, int[] y){
		int[] results = new int[x.length+y.length];
		for (int i=0 ; i<x.length ; i++){
			results[i] = x[i];
		}
		for (int i=0 ; i<y.length ; i++){
			results[x.length+i] = y[i];
		}
		return results;
	}
	
	private int[] LSDcountingSortOnDigit(int[] toSort, int digit){
		int[] count = new int[10];
		int[] tempArray = new int[toSort.length];
		
		// determine how big each bucket should be.
		for (int i : toSort){
			count[getNthDigit(i, 10, digit+1)]++;
		}
		// determine the ending index of each bucket
		// on a single array. (aka offset)
		for (int i=1 ; i<10 ; i++){
			count[i]+=count[i-1];
		}
		// input each entry into a single array at the ending index 
		// of its bucket and minus the ending index by 1
		for (int i=toSort.length-1 ; i>=0 ; i--){
			tempArray[--count[getNthDigit(toSort[i], 10, digit+1)]] = toSort[i];
		}
		return tempArray;
	}
	
	private int[][] MSDcountingSortOnDigit(int[] toSort, int digit){
		int[] count = new int[10];
		int[][] tempArray = new int[10][];
		// determine how big each bucket should be.
		for (int i : toSort){
			count[getNthDigit(i, 10, digit+1)]++;
		}
		for (int i=0 ; i<10 ; i++){
			tempArray[i] = new int[count[i]];
		}
		// input each entry into a single array at the ending index 
		// of its bucket and minus the ending index by 1
		for (int i=toSort.length-1 ; i>=0 ; i--){
			int temp_digit = getNthDigit(toSort[i], 10, digit+1);
			tempArray[temp_digit][--count[temp_digit]] = toSort[i];
		}
		return tempArray;
	}
	
	/********************************************
	 * 
	 * 		Methods
	 * 
	 ********************************************/
	public int[] countingSort(int[] toSort){
		// Count the number of buckets (aka the number of different keys)
		int max = toSort[0];
		for (int i=0 ; i< toSort.length ; i++){
			if (toSort[i] > max){
				max = toSort[i];
			}
		}
		// To account for that the key 0 is also a key.
		max+=1; 
		
		// this array keeps count of the size of each bucket
		int[] count = new int[max]; 
		
		// determine how big each bucket should be.
		// (aka histogram of the frequencies)
		for (int i : toSort){
			count[i]++;
		}
		
		// determine the ending index of each bucket
		// on a single array. (aka offset)
		for (int i=1 ; i<count.length ; i++){
			count[i]+=count[i-1];
		}
		
		// input each entry into a single array at the ending index 
		// of its bucket and minus the ending index by 1
		int[] tempArray = new int[toSort.length];
		for (int i=toSort.length-1 ; i>=0 ; i--){
			tempArray[--count[toSort[i]]] = toSort[i];
		}
		return tempArray;
	}
	
	public int[] radixSort_LSD(int[] toSort){
		int max = toSort[0];
		for (int i : toSort){
			if (max < i){
				max = i;
			}
		}
		return radixSort_LSD(toSort, String.valueOf(max).length()-1);
	}
	private int[] radixSort_LSD(int[] toSort, int digit){
		if (digit == 0){
			return LSDcountingSortOnDigit(toSort, digit);
		} else {
			toSort = radixSort_LSD(toSort, digit-1);
			return LSDcountingSortOnDigit(toSort, digit);
		}
	}
	
	public int[] radixSort_MSD(int[] toSort){
		int max = toSort[0];
		for (int i : toSort){
			if (max < i){
				max = i;
			}
		}
		return radixSort_MSD(toSort, String.valueOf(max).length()-1);
	}
	private int[] radixSort_MSD(int[] toSort, int digit){
		if (digit == 0){
			int[][] temp_array = MSDcountingSortOnDigit(toSort, digit);
			for ( int i=1 ; i<temp_array.length ; i++ ){
				temp_array[0] = concat(temp_array[0], temp_array[i]);
			}
			return temp_array[0];
		} else {
			int[][] temp_array = MSDcountingSortOnDigit(toSort, digit);
			for ( int i=0 ; i<temp_array.length ; i++){
				temp_array[i] = radixSort_MSD(temp_array[i], digit-1);
			}
			for ( int i=1 ; i<temp_array.length ; i++ ){
				temp_array[0] = concat(temp_array[0], temp_array[i]);
			}
			return temp_array[0];
		}
	}
}
