package ua.edu.lp.reliability.math.models;

/**
 * Model for reliability prediction by Gole-Okumoto
 * 
 * @author iurii
 * 
 */
public class GoelOkumotoModel implements ReliabilityModel {

	private final double[] Yn;

	private final double[] Yn1;

	private final double[] intervals;

	private final double[] errors;

	private final double k;

	private final int N;

	private final double eps;

	public GoelOkumotoModel(double[] intervals, double[] errors, double[] Yn,
			double eps) {
		this.intervals = intervals;
		this.errors = errors;
		this.Yn = Yn;
		this.eps = eps;
		this.N = this.intervals.length;

		double[] tmp = new double[2];
		tmp[0] = 0;
		tmp[1] = 0;
		this.Yn1 = tmp;

		double tmpK = 0;
		for (int i = 0; i < N; i++) {
			tmpK += errors[i];
		}
		this.k = tmpK;

	}

	public double firstFunc(double x, double y) {
		return (k / (1 - Math.exp(-y * intervals[N - 1]))) - x;
	}

	// Функція вираження другої формули моделі
	public double secondFunc(double x, double y) {
		double res = 0;
		double tmp = 0;
		for (int i = 1; i < N; i++) {
			tmp = tmp
					+ ((errors[i] * (intervals[i] * Math.exp(-y * intervals[i]) - intervals[i - 1]
							* Math.exp(-y * intervals[i - 1]))) / (Math.exp(-y
							* intervals[i - 1]) - Math.exp(-y * intervals[i])));
		}

		res = tmp - (intervals[N - 1] * Math.exp(-y * intervals[N - 1]) * k)
				/ (1 - Math.exp(-y * intervals[N - 1]));

		return res;

	}

	// Функція вираження похідної f1 по x
	public double derF1x(double x, double y) {

		return -1;

	}

	// Функція вираження похідної f1 по y
	public double derF1y(double x, double y) {

		return -(k * (Math.exp(-y * intervals[N - 1]) * intervals[N - 1]) / Math
				.pow((Math.exp(-y * intervals[N - 1]) - 1), 2));
	}

	// Функція вираження похідної f2 по x
	public double derF2x(double x, double y) {
		return 0;
	}

	// Функція вираження похідної f2 по y
	public double derF2y(double x, double y) {

		double f21 = 0;
		double f22 = 0;

		for (int i = 1; i < N; i++) {
			f21 = f21
					+ (((Math.exp(-y * intervals[i - 1])
							* Math.pow(intervals[i - 1], 2) - Math.exp(-y
							* intervals[i])
							* Math.pow(intervals[i], 2)) * k)
							/ (Math.exp(-y * intervals[i - 1]) - Math.exp(-y
									* intervals[i])) - ((Math.exp(-y
							* intervals[i - 1])
							* intervals[i - 1] - Math.exp(-y * intervals[i])
							* intervals[i]) * k)
							/ Math.pow((Math.exp(-y * intervals[i - 1]) - Math
									.exp(-y * intervals[i])), 2));
		}

		f22 = (intervals[N - 1] * Math.exp(-y * intervals[N - 1]) * k)
				/ (1 - Math.exp(-y * intervals[N - 1]));

		return f21 - f22;
	}

	// Функція реалізації матриці Якобі
	public double[][] derFmatrix(double x, double y) {
		double[][] Fd = new double[2][2];
		double D = 0;

		D = derF1x(x, y) * derF2y(x, y) - derF1y(x, y) * derF2x(x, y);

		Fd[0][0] = derF2y(x, y) / D;
		Fd[0][1] = -(derF1y(x, y) / D);
		Fd[1][0] = -(derF2x(x, y) / D);
		Fd[1][1] = derF1x(x, y) / D;

		return Fd;

	}

	// Функція реалізації значення матриці F
	public double[] F(double x, double y) {
		double[] F = new double[2];

		F[0] = firstFunc(x, y);
		F[1] = secondFunc(x, y);

		return F;
	}

	// Реалізація методу Нютона
	public void nyutonMethod() {

		double[][] derF = new double[2][2];
		double[] funcMatrix = new double[2];
		double[] funcRes = new double[2];
		double abs = 0;

		while (true) {
			funcMatrix = F(Yn[0], Yn[1]);
			derF = derFmatrix(Yn[0], Yn[1]);

			funcRes[0] = funcMatrix[0] * derF[0][0] + funcMatrix[1]
					* derF[1][0];
			funcRes[1] = funcMatrix[0] * derF[0][1] + funcMatrix[1]
					* derF[1][1];

			Yn1[0] = Yn[0] - funcRes[0];
			Yn1[1] = Yn[1] - funcRes[1];

			abs = Math.abs(Yn[1] - Yn1[1]);

			if (abs > eps) {
				Yn[0] = Yn1[0];
				Yn[1] = Yn1[1];
			} else {
				break;
			}
		}
	}

	@Override
	public double[] calculate() {

		nyutonMethod();

		return Yn1;
	}

	public double[] getYn() {
		return this.Yn;
	}

	public double[] getYn1() {
		return this.Yn1;
	}

	public double[] getIntervals() {
		return this.intervals;
	}

	public double[] getErrors() {
		return this.errors;
	}

	public int getN() {
		return this.N;
	}
}
