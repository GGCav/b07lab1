import java.io.FileWriter;
import java.util.Scanner;
import java.io.*;

public class Polynomial{
	double[] ma = new double[1024];
	int[] ex = new int[1024];
	int len;
	
	public Polynomial(){
		for (int i = 0; i<this.ma.length; i++) {
			this.ma[i] = 0;
			this.ex[i] = 0;
		}
		this.len = 0;
	}
	public Polynomial(double[] news) {
		for (int i =0; i<news.length; i++) {
			if (news[i] == 0) {
				continue;
			}
			this.ma[len] = news[i];
			this.ex[len] = i;
			this.len++;
		}
	}
	public Polynomial(File ff) {
		 try
		    {
				Scanner input = new Scanner(ff);
				String temp = input.nextLine();


				this.len = 0;
				String part = "";
				if (temp.charAt(0) == '0') {
					return;
				}
				for (int i =0; i<temp.length(); i++) {
					if (temp.charAt(i) == '+') {
						String[] split = part.split("x");
						this.ma[this.len] = Double.parseDouble(split[0]);
						if (split.length == 2) {
							this.ex[this.len] = Integer.parseInt(split[1]);
						}else {
							this.ex[this.len] = 0;
						}
						part = "";
						this.len++;
						continue;
					}else if (temp.charAt(i) == '-') {
						if (i == 0) {
							part = "-";
							continue;
						}
						String[] split = part.split("x");
						this.ma[this.len] = Double.parseDouble(split[0]);
						if (split.length == 2) {
							this.ex[this.len] = Integer.parseInt(split[1]);
						}else {
							this.ex[this.len] = 0;
						}
						part = "-";
						this.len++;
						continue;
					}
					
					part += temp.charAt(i);
				}
				if (part != "") {
					String[] split = part.split("x");
					this.ma[this.len] = Double.parseDouble(split[0]);
					if (split.length == 2) {
						this.ex[this.len] = Integer.parseInt(split[1]);
					}else {
						this.ex[this.len] = 0;
					}
				}
				this.len++;
				
				int temp1;
				double temp2;
				for (int i=0; i<this.len-1; i++) {
					for (int j=0; j<this.len-1-i; j++) {
						if (this.ex[j] > this.ex[j+1]) {
							temp1 = this.ex[j];
							temp2 = this.ma[j];
							this.ex[j] = this.ex[j+1];
							this.ex[j+1] = temp1;
							this.ma[j] = this.ma[j+1];
							this.ma[j+1] = temp2;
						}
					}
				}
		    }catch(Exception e) {}
	}

	public Polynomial add(Polynomial news) {
		Polynomial ans = new Polynomial();
		ans = this;
		for (int i = 0; i<news.len; i++) {
			boolean f = false;
			for (int j = 0; j<ans.len; j++) {
				if (ans.ex[j] == news.ex[i]) {
					ans.ma[j] += news.ma[i];
					f = true;
					break;
				}
			}
			if (!f) {
				ans.ma[ans.len] = news.ma[i];
				ans.ex[ans.len] = news.ex[i];
				ans.len++;
			}
		}
		int temp1;
		double temp2;
		for (int i=0; i<ans.len-1; i++) {
			for (int j=0; j<ans.len-1-i; j++) {
				if (ans.ex[j] > ans.ex[j+1]) {
					temp1 = ans.ex[j];
					temp2 = ans.ma[j];
					ans.ex[j] = ans.ex[j+1];
					ans.ex[j+1] = temp1;
					ans.ma[j] = ans.ma[j+1];
					ans.ma[j+1] = temp2;
				}
			}
		}
		return ans;
	}
	
	public Polynomial multiply(Polynomial news) {
		Polynomial ans= new Polynomial();
		if (this.ex[0]==0 && this.ma[0] == 0) {
			return this;
		}
		if (news.ex[0]==0 && news.ma[0] == 0) {
			return news;
		}
		for (int i = 0; i < this.len; i++) {
			for(int j = 0; j<news.len; j++) {
				int newex = this.ex[i] * news.ex[j];
				boolean f = false;
				for (int k = 0; k<ans.len; k++) {
					if (newex == ans.ex[k]) {
						ans.ma[k] += this.ma[i] * news.ma[j];
						f = true;
					}
				}
				if (!f) {
					ans.ex[ans.len] = this.ex[i] * news.ex[j];
					ans.ma[ans.len] = this.ma[i] * news.ma[j];
					ans.len++;
				}
			}
		}
		
		int temp1;
		double temp2;
		for (int i=0; i<ans.len-1; i++) {
			for (int j=0; j<ans.len-1-i; j++) {
				if (ans.ex[j] > ans.ex[j+1]) {
					temp1 = ans.ex[j];
					temp2 = ans.ma[j];
					ans.ex[j] = ans.ex[j+1];
					ans.ex[j+1] = temp1;
					ans.ma[j] = ans.ma[j+1];
					ans.ma[j+1] = temp2;
				}
			}
		}
		return ans;
	}
	
	public double evaluate(double x) {
		double ans = 0;
		for (int i = 0; i<this.len; i++) {
			ans += this.ma[i] * Math.pow(x,this.ex[i]);
		}	
		return ans;
	}
	public boolean hasRoot(int root) {
		return (int) this.evaluate(root) == 0;
	}
	
	public void saveToFile(String fname) {
		try {
		File ff = new File(fname);
		FileWriter oot = new FileWriter(ff, true);
		for (int i = 0; i<this.len; i++) {
			if (i == 0) {
				if (this.ex[i] == 0) {
					oot.write(this.ma[i]+"");
					continue;
				}
			}else if (this.ma[i] > 0) {
				oot.write('+');
			}
			if (this.ex[i] == 0) {
				oot.write(this.ma[i]+"");
			}else{
				oot.write(this.ma[i]+"");
				oot.write('x');
				oot.write(this.ex[i]+"");
			}
		}
		oot.write("\n");
		oot.flush();
		oot.close();
	}
	catch (Exception e) {}
	}
}