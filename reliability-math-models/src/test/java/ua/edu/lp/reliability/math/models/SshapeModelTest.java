package ua.edu.lp.reliability.math.models;

public class SshapeModelTest {
	// Test data
	private int intervalNum = 120;

	private int intervalSize = 10;

	private double[] errors = new double[] { 9.40, 6.43, 3.95, 2.48, 1.43,
			1.15, 1.08, 0.93, 0.90, 0.63, 0.60, 0.48, 0.45, 0.40, 0.28, 0.35,
			0.28, 0.28, 0.15, 0.18, 0.10, 0.15, 0.15, 0.18, 0.08, 0.08, 0.05,
			0.18, 0.13, 0.13, 0.08, 0.03, 0.05, 0.05, 0.05, 0, 0, 0, 0.03,
			0.08, 0.03, 0, 0.05, 0.05, 0.03, 0.08, 0.03, 0.05, 0, 0, 0, 0.05,
			0.03, 0, 0, 0.05, 0, 0.08, 0.03, 0.03, 0.03, 0.03, 0.03, 0, 0.05,
			0, 0.03, 0.03, 0, 0, 0.03, 0.05, 0, 0, 0, 0, 0, 0.03, 0, 0, 0, 0,
			0.03, 0, 0, 0, 0.03, 0, 0, 0.03, 0, 0.03, 0, 0.05, 0, 0, 0, 0,
			0.03, 0, 0, 0, 0.03, 0, 0, 0, 0, 0.03, 0.03, 0, 0, 0, 0.03, 0.03,
			0, 0, 0, 0, 0, 0, };

	private double[] Y0 = new double[] { 20, 0.01 };

	private double eps = 0.00001;

	private SshapeModel sShapeModel;

	public void init() {
		double[] intervals = new double[120];

		for (int i = 0; i < intervalNum; i++) {
			intervals[i] = i + intervalSize;
		} 

		sShapeModel = new SshapeModel(intervals, errors, Y0, eps);
	}

	public double[] testCalculate() {
		double[] Yn = sShapeModel.calculate();
		return Yn;
	}

	public static void main(String[] args) {
		// Given
		SshapeModelTest sShapeTest = new SshapeModelTest();

		// When
		sShapeTest.init();

		// Then
		double[] Yn = sShapeTest.testCalculate();
		System.out.println("L = " + Yn[0] + "; B = " + Yn[1]);
	}
}
