package com.netcracker.edu.java.tasks;

public class ComplexNumberImpl implements ComplexNumber {

	private double im;
	private double re;
	
	public ComplexNumberImpl(double re, double im) {
		this.re = re;
		this.im = im;	
	}
	public ComplexNumberImpl() {
	}
	@Override
	public double getRe() {
		return re;
	}

	@Override
	public double getIm() {
		return im;
	}

	@Override
	public boolean isReal() {
		if(im ==0) {
			return true;
		}
		else
			return false;
	}

	@Override
	public void set(double re, double im) {
		this.re = re;
		this.im = im;
		
	}

	@Override
    public void set(String value) throws NumberFormatException {	
        String rePart = "";
        String imPart = "";
        if(value.charAt(value.length() - 1) != 'i'){
            rePart = value;
            imPart = "0";
        }
        else{
            for(int i=value.length() - 1;i>-1;i--){
                if(value.charAt(i)=='-' ||value.charAt(i)=='+') {
                    imPart = new String(value.substring(i, value.length()));
                    rePart = new String(value.substring(0, i));
                    if(rePart.isEmpty()){
                        rePart = "0"; 
                    }
                    if(imPart.compareTo("-i") == 0 || imPart.compareTo("+i") == 0){
                        imPart = imPart.replace('i', '1');
                    }
                    imPart = imPart.replace('i',' ');
                    break;
                }
                else {
                    imPart = value;
                    if(imPart.compareTo("-i") == 0 || imPart.compareTo("+i") == 0){
                        imPart = imPart.replace('i', '1');
                    }
                    
                    imPart = imPart.replace('i',' ');
                    rePart = "0";
                }
            }
        }
        try{
        re = Double.parseDouble(rePart);
        im = Double.parseDouble(imPart);
        }
        catch(NumberFormatException e){
            throw new IllegalArgumentException("The value entered, " + value+ " is invalid.", e);
        }
    }

	@Override
	public ComplexNumber copy() {
		ComplexNumber copy = new ComplexNumberImpl();
		copy.set(getRe(), getIm());
		return copy;
	}

	@Override
	public ComplexNumber clone() throws CloneNotSupportedException {
		ComplexNumber clone = new ComplexNumberImpl();
		clone.set(getRe(), getIm());
		return clone;
	}
	public String toString() {
		String result;
		if(im > 0){
            if(re == 0){
                result = Double.toString(im) +"i";
            }
            else{
            result = Double.toString(re)+ "+"+Double.toString(im) +"i";
        }}
        else if(im ==0){
                result = Double.toString(re);
            }
        if(re == 0){
            result = Double.toString(im) +"i";
        }
            else{
                result = Double.toString(re)+Double.toString(im) +"i";
        }
    return result;	
	}
	public boolean equals(Object other) {
		return false;
		
	}

	@Override
	public int compareTo(ComplexNumber other) {
		double otherIm = other.getIm();
		double otherRe = other.getRe();
		int result = 0;
		if(im*im+re*re > otherIm*otherIm + otherRe*otherRe) {
			result = 1;
		}
		else if(im*im+re*re == otherIm*otherIm + otherRe*otherRe) {
			result = 0;
		}
		else if(im*im+re*re < otherIm*otherIm + otherRe*otherRe) {
			result = -1;
		}
		return result;
	}

	@Override
	public void sort(ComplexNumber[] array) {
		int result;
		for (int i = array.length - 1; i >= 2; i--) {
		    boolean sorted = true;
		    for (int j = 0; j < i; j++) {
		    	result = array[j].compareTo(array[j+1]);
		        if (result == 1) {
		            ComplexNumber temp = array[j];
		            array[j] = array[j+1];
		            array[j+1] = temp;
		            sorted = false;
		        }
		    }
		    if(sorted) {
		        break;
		    }
		}
		
	}

	@Override
	public ComplexNumber negate() {
		set(-re, -im);
		return this;
	}

	@Override
	public ComplexNumber add(ComplexNumber arg2) {
		set(re+arg2.getRe(), im+arg2.getIm());
		return this;
	}

	@Override
	public ComplexNumber multiply(ComplexNumber arg2) {		
		set(re*arg2.getRe()-im*arg2.getIm(), im*arg2.getRe()+re*arg2.getIm());
		return this;
	}

}
