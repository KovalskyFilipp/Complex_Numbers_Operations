package ru.ncedu.java.tasks;

public class ArrayVectorImpl implements ArrayVector {
	
	double[] doubleArray;

	public ArrayVectorImpl() {
	}

	@Override
	public void set(double... elements) {		
		 doubleArray = elements;		
	}

	@Override
	public double[] get() {
		
		return doubleArray;
	}

	@Override
	public ArrayVector clone() {
		ArrayVector clone = new ArrayVectorImpl();
		double[] cloneDoubleArray = doubleArray.clone();
		clone.set(cloneDoubleArray);
		return clone;
	}

	@Override
	public int getSize() {
		return doubleArray.length;
	}

	@Override
	public void set(int index, double value) {
		if(index >= 0 && index < doubleArray.length) {
			doubleArray[index] = value;
		}
		if(index < 0) {
			return ;
		}
		if(index >= doubleArray.length) {
			double[] newDoubleArray = new double[index +1];
			System.arraycopy(doubleArray, 0, newDoubleArray, 0, doubleArray.length);
			doubleArray = newDoubleArray;
			doubleArray[index] = value;
		}
		
	}

	@Override
	public double get(int index) throws ArrayIndexOutOfBoundsException {
		if(index < 0 || index > doubleArray.length) {
			throw new ArrayIndexOutOfBoundsException("Array Index Out of Bounds");
		}
		return doubleArray[index];
	}

	@Override
	public double getMax() {
		double max = doubleArray[0];
		for(int i=0; i<doubleArray.length;i++) {
			if(doubleArray[i]> max) {
				max = doubleArray[i];
			}
		}
		return max;
	}

	@Override
	public double getMin() {
		double min = doubleArray[0];
		for(int i=0; i<doubleArray.length;i++) {
			if(doubleArray[i]< min) {
				min = doubleArray[i];
			}
		}
		return min;
	}

	@Override
	public void sortAscending() {
		for (int i = doubleArray.length - 1; i >= 2; i--) {
		    boolean sorted = true;
		    for (int j = 0; j < i; j++) {
		        if (doubleArray[j] > doubleArray[j+1]) {
		            double temp = doubleArray[j];
		            doubleArray[j] = doubleArray[j+1];
		            doubleArray[j+1] = temp;
		            sorted = false;
		        }
		    }
		    if(sorted) {
		        break;
		    }
		}
	}

	@Override
	public void mult(double factor) {
		for(int i = 0;i<doubleArray.length;i++) {
			doubleArray[i] = doubleArray[i]*factor;
		}
	}

	@Override
	public ArrayVector sum(ArrayVector anotherVector) {
		ArrayVector sumVector = new ArrayVectorImpl();
		double[] anotherVectorDouble = anotherVector.get();
		for(int i=0;i<doubleArray.length;i++) {
			doubleArray[i]=doubleArray[i]+anotherVectorDouble[i];
		}
		sumVector.set(doubleArray);	
		return sumVector;
	}

	@Override
	public double scalarMult(ArrayVector anotherVector) {
		double result = 0;
		double[] anotherVectorDouble = anotherVector.get();
		for(int i=0;i<doubleArray.length;i++) {
			result+=doubleArray[i]*anotherVectorDouble[i];
		}
		return result;
	}

	@Override
	public double getNorm() {
		double result=0;
		ArrayVector normOfVector = new ArrayVectorImpl();
		normOfVector.set(doubleArray);
		result=Math.sqrt(normOfVector.scalarMult(normOfVector));
		return result;
	}

}
