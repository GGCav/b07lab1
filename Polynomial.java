public class Polynomial{
	double[] A = new double[5];
	
	public Polynomial(){
		A[0] = 0;
	}
	public Polynomial(double[] para) {
		for (int i = 0; i<para.length; i++) {
			A[i] = para[i];
		}
	}
	public Polynomial add(Polynomial B) {
		for (int i = 0; i<B.A.length; i++) {
			B.A[i] += A[i];
		}	
		return B;
	}
	public double evaluate(double x) {
		double ans = 0;
		for (int i = 0; i<A.length; i++) {
			ans += A[i] * Math.pow(x,i);
		}	
		return ans;
	}
	public boolean hasRoot(int root) {
		return (int) evaluate(root) == 0;
	}
}