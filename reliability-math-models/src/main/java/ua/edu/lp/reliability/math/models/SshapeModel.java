package ua.edu.lp.reliability.math.models;

public class SshapeModel implements ReliabilityModel {

	private final double[] Xk;

	private final double[] Xkp1;

	private final double[] T;

	private final double[] M;

	private final double sumMk;

	private final int N;

	private double Err = 0;

	public SshapeModel(double[] Te, double[] Me, double[] Xke, double e) {
		this.T = Te;
		this.M = Me;
		this.Xk = Xke;
		this.Err = e;
		this.N = this.T.length;

		double[] TMP = new double[2];
		TMP[0] = 0;
		TMP[1] = 0;
		this.Xkp1 = TMP;

		double tmpMk = 0;
		for (int i = 0; i < N; i++) {
			tmpMk += M[i];
		}

		this.sumMk = tmpMk;
	}

	// First model equation
	private double f1(double x, double y) {

		double tn = this.T[N - 1];
		double result = 0;

		result = (sumMk) / (1 - (1 + y * tn) * Math.exp(-y * tn)) - x;

		return result;
	}

	// Second model equation
	private double f2(double x, double y) {
		double sum = 0;
		double tn = this.T[N - 1];
		double result = 0;
		int n = this.N;

		for (int i = 1; i < n; i++) {
			sum += (M[i] * (y * T[i] * T[i] * Math.exp(-y * T[i]) - y
					* T[i - 1] * T[i - 1] * Math.exp(-y * T[i - 1])))
					/ ((1 - (1 + y * T[i]) * Math.exp(-y * T[i])) - (1 - (1 + y
							* T[i - 1])
							* Math.exp(-y * T[i - 1])));
		}

		result = sum + x * y * tn * tn * Math.exp(-y * tn);

		return result;
	}

	private double df1x(double x, double y) {
		return -1;
	}

	private double df1y(double x, double y) {
		double mkSum = sumMk;
		double tn = this.T[N - 1];
		double result = 0;

		result = (mkSum * (Math.exp(-y * tn) * tn - Math.exp(-y * tn)
				* (y * tn + 1) * tn))
				/ Math.pow((Math.exp(-y * tn) * (y * tn + 1) - 1), 2);

		return result;
	}

	private double df2x(double x, double y) {
		double tn = 0;
		double result = 0;

		result = y * Math.exp(-y * tn) * tn * tn;

		return result;
	}

	private double df2y(double x, double y) {
		double result = 0;

		double tn = this.T[N - 1];
		double f22 = 1;
		double f21 = 0;
		double f21t1 = 0;
		double f21t2 = 0;
		int n = this.N;

		f22 = x * Math.exp(-y * tn) * tn * tn - x * y * Math.exp(-y * tn) * tn
				* tn * tn;

		for (int i = 1; i < n; i++) {

			f21t1 = (M[i]
					* (y * Math.exp(-y * T[i - 1]) * Math.pow(T[i - 1], 2) - y
							* Math.exp(-y * T[i]) * Math.pow(T[i], 2)) * (Math
					.exp(-y * T[i - 1])
					* T[i - 1]
					- Math.exp(-y * T[i])
					* T[i]
					+ Math.exp(-y * T[i]) * (y * T[i] + 1) * T[i] - Math.exp(-y
					* T[i - 1])
					* (y * T[i - 1] + 1) * T[i - 1]))
					/ Math.pow(
							(Math.exp(-y * T[i - 1]) * (y * T[i - 1] + 1) - Math
									.exp(-y * T[i]) * (y * T[i] + 1)), 2);

			f21t2 = M[i]
					* (Math.exp(-y * T[i - 1]) * Math.pow(T[i - 1], 2) - y
							* Math.exp(-y * T[i - 1]) * Math.pow(T[i - 1], 3)
							+ y * Math.exp(-y * T[i]) * Math.pow(T[i], 3) - Math
							.exp(-y * T[i]) * Math.pow(T[i - 1], 2))
					/ (Math.exp(-y * T[i - 1]) * (y * T[i - 1] + 1) - Math
							.exp(-y * T[i]) * (y * T[i] + 1));

			f21 = f21 + (f21t1 - f21t2);
		}

		result = f21 + f22;

		return result;
	}

	// Jacobi matrix
	private double[][] Fd(double x, double y) {
		double[][] Fd = new double[2][2];
		double D = 0;

		D = df1x(x, y) * df2y(x, y) - df1y(x, y) * df2x(x, y); // детермінант

		Fd[0][0] = df2y(x, y) / D;
		Fd[0][1] = -(df1y(x, y) / D);
		Fd[1][0] = -(df2x(x, y) / D);
		Fd[1][1] = df1x(x, y) / D;

		return Fd;
	}

	// Function for matrix F
	public double[] F(double x, double y) {
		double[] F = new double[2];

		F[0] = f1(x, y);
		F[1] = f2(x, y);

		return F;
	}

	// Nyuton method
	public void Nyuton() {
		double[][] Fdr = new double[2][2];
		double[] Fr = new double[2];
		double[] Fres = new double[2];
		double abs = 0;

		while (true) {
			Fr = F(Xk[0], Xk[1]);
			Fdr = Fd(Xk[0], Xk[1]);

			Fres[0] = Fr[0] * Fdr[0][0] + Fr[1] * Fdr[1][0];
			Fres[1] = Fr[0] * Fdr[0][1] + Fr[1] * Fdr[1][1];

			Xkp1[0] = Xk[0] - Fres[0];
			Xkp1[1] = Xk[1] - Fres[1];

			abs = Math.abs(Xk[1] - Xkp1[1]);

			if (abs > Err) {
				Xk[0] = Xkp1[0];
				Xk[1] = Xkp1[1];
			} else {
				break;
			}
		}
	}

	@Override
	public double[] calculate() {

		Nyuton();

		return Xkp1;
	}

	public double[] getXk() {
		return this.Xk;
	}

	public double[] getXkp1() {
		return this.Xkp1;
	}

	public double[] getT() {
		return this.T;
	}

	public double[] getM() {
		return this.M;

	}

	public int getN() {
		return this.N;
	}
}
