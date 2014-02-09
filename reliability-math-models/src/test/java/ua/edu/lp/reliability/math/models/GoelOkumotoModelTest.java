package ua.edu.lp.reliability.math.models;

import java.text.DecimalFormat;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for reliability prediction model by Gole-Okumoto.
 * 
 * @author Iurii Parfeniuk
 * 
 */
public class GoelOkumotoModelTest {

	@Test
	public void testCalculate() {
		// Given
		int intervalNum = 120;
		int intervalSize = 10;
		double[] Y0 = new double[] { 20, 0.01 };
		double eps = 0.00001;

		double[] errors = new double[] { 9.40, 6.43, 3.95, 2.48, 1.43, 1.15, 1.08, 0.93, 0.90, 0.63, 0.60, 0.48, 0.45, 0.40, 0.28, 0.35, 0.28, 0.28, 0.15,
				0.18, 0.10, 0.15, 0.15, 0.18, 0.08, 0.08, 0.05, 0.18, 0.13, 0.13, 0.08, 0.03, 0.05, 0.05, 0.05, 0, 0, 0, 0.03, 0.08, 0.03, 0, 0.05, 0.05, 0.03,
				0.08, 0.03, 0.05, 0, 0, 0, 0.05, 0.03, 0, 0, 0.05, 0, 0.08, 0.03, 0.03, 0.03, 0.03, 0.03, 0, 0.05, 0, 0.03, 0.03, 0, 0, 0.03, 0.05, 0, 0, 0, 0,
				0, 0.03, 0, 0, 0, 0, 0.03, 0, 0, 0, 0.03, 0, 0, 0.03, 0, 0.03, 0, 0.05, 0, 0, 0, 0, 0.03, 0, 0, 0, 0.03, 0, 0, 0, 0, 0.03, 0.03, 0, 0, 0, 0.03,
				0.03, 0, 0, 0, 0, 0, 0, };

		double[] intervals = createIntervals(intervalNum, intervalSize);

		// When
		GoelOkumotoModel goelOkumotoMethod = new GoelOkumotoModel(intervals, errors, Y0, eps);

		double[] Yn = goelOkumotoMethod.calculate();

		// Then
		printResult(calculateSum(errors), Yn[0], Yn[1]);

		DecimalFormat numberFormater = new DecimalFormat("#.##");
		Assert.assertEquals("37.86", numberFormater.format(Yn[0]));
		Assert.assertEquals("0.02", numberFormater.format(Yn[1]));
	}

	@Test
	public void testCalculateJiraIssues_ApacheJackRabbit() {
		// Given
		int intervalNum = 109;
		int intervalSize = 30;
		double[] intervals = createIntervals(intervalNum, intervalSize);
		double[] Y0 = new double[] { 5000, 0.01 };
		double eps = 0.00001;

		double[] errors = new double[] { 3, 11, 8, 14, 3, 12, 38, 27, 9, 23, 25, 27, 26, 38, 22, 11, 13, 18, 52, 37, 27, 25, 56, 27, 27, 32, 43, 37, 31, 46,
				60, 43, 59, 39, 50, 56, 48, 45, 48, 25, 72, 89, 65, 57, 70, 29, 34, 26, 50, 66, 40, 26, 39, 40, 46, 40, 33, 50, 60, 50, 47, 36, 43, 34, 32, 42,
				66, 32, 23, 25, 21, 44, 30, 37, 24, 30, 16, 33, 31, 17, 28, 29, 27, 27, 26, 45, 29, 33, 28, 21, 37, 17, 27, 41, 33, 16, 16, 19, 18, 16, 19, 18,
				26, 31, 17, 9, 18, 18, 7 };

		GoelOkumotoModel goelOkumotoMethod = new GoelOkumotoModel(intervals, errors, Y0, eps);

		// When
		double[] Yn = goelOkumotoMethod.calculate();

		// Then
		printResult(calculateSum(errors), Yn[0], Yn[1]);

		DecimalFormat numberFormater = new DecimalFormat("#.##");
		Assert.assertEquals("3657.87", numberFormater.format(Yn[0]));
		Assert.assertEquals("0.03", numberFormater.format(Yn[1]));
	}

	@Test
	public void testCalculateJiraIssues_ApacheJackRabbit_ByReporter() {
		// Given
		int intervalNum = 109;
		int intervalSize = 30;
		double[] intervals = createIntervals(intervalNum, intervalSize);
		double eps = 0.00001;
		double[] Y0 = new double[] { 200, 0.01 };

		double[] errors = new double[] { 1, 2.75, 2.667, 3.5, 1, 2, 2.375, 2.25, 1.125, 2.091, 2.083, 2.25, 1.733, 2.923, 1.571, 1.571, 1.444, 1.5, 3.467,
				1.762, 2.077, 2.083, 3.294, 1.8, 1.929, 1.684, 2.15, 2.312, 2.385, 2, 2.5, 1.654, 2.269, 1.95, 2.5, 2.074, 2.182, 1.731, 3, 1.923, 2.057,
				2.472, 2.5, 2.375, 2.8, 1.45, 1.545, 1.733, 2.273, 2.75, 2.667, 2, 1.625, 2.353, 2.875, 3.077, 2.75, 2.941, 3.333, 2.941, 2.238, 1.714, 1.87,
				1.619, 3.2, 2.333, 2.87, 1.882, 1.533, 1.562, 1.312, 2.2, 2.727, 2.056, 1.5, 3, 1.455, 1.833, 1.722, 2.429, 2, 1.611, 1.688, 2.25, 2.364, 2.5,
				2.071, 1.737, 2, 1.312, 2.056, 1.417, 2.077, 1.952, 1.941, 1.143, 1.6, 1.118, 1.5, 1.6, 1.9, 1.385, 1.444, 1.938, 1.545, 2.25, 1.385, 2, 1,

		};

		GoelOkumotoModel goelOkumotoMethod = new GoelOkumotoModel(intervals, errors, Y0, eps);

		// When
		double[] Yn = goelOkumotoMethod.calculate();

		// Then
		printResult(calculateSum(errors), Yn[0], Yn[1]);

		DecimalFormat numberFormater = new DecimalFormat("#.##");
		Assert.assertEquals("224.94", numberFormater.format(Yn[0]));
		Assert.assertEquals("0.07", numberFormater.format(Yn[1]));
	}

	@Test
	public void testCalculateJiraIssues_ApacheStruts() {
		// Given
		int intervalNum = 142;
		int intervalSize = 30;
		double[] intervals = createIntervals(intervalNum, intervalSize);
		double[] Y0 = new double[] { 6000, 0.01 };
		double eps = 0.00001;

		double[] errors = new double[] { 34, 12, 1, 9, 11, 9, 12, 7, 16, 9, 22, 21, 35, 17, 16, 45, 43, 25, 50, 28, 15, 29, 24, 18, 12, 39, 24, 14, 26, 17, 18,
				22, 19, 14, 14, 20, 6, 13, 12, 9, 17, 90, 46, 79, 73, 84, 56, 25, 25, 36, 35, 26, 33, 25, 45, 59, 102, 87, 84, 36, 59, 61, 56, 72, 70, 58, 61,
				57, 66, 56, 45, 53, 40, 38, 43, 48, 30, 35, 42, 43, 34, 41, 51, 46, 28, 31, 36, 21, 36, 44, 34, 18, 17, 19, 26, 18, 9, 10, 10, 17, 19, 16, 12,
				15, 16, 14, 21, 16, 20, 19, 9, 7, 14, 8, 19, 19, 17, 18, 19, 20, 24, 13, 12, 14, 12, 28, 20, 24, 20, 15, 45, 28, 24, 46, 29, 21, 28, 24, 11,
				17, 17, 4 };

		GoelOkumotoModel goelOkumotoMethod = new GoelOkumotoModel(intervals, errors, Y0, eps);

		// When
		double[] Yn = goelOkumotoMethod.calculate();

		// Then
		printResult(calculateSum(errors), Yn[0], Yn[1]);

		DecimalFormat numberFormater = new DecimalFormat("#.##");
		Assert.assertEquals("4244.68", numberFormater.format(Yn[0]));
		Assert.assertEquals("0.03", numberFormater.format(Yn[1]));
	}

	@Test
	public void testCalculateJiraIssues_ApacheStruts_ByReporter() {
		// Given
		int intervalNum = 142;
		int intervalSize = 30;
		double[] intervals = createIntervals(intervalNum, intervalSize);
		double eps = 0.00001;
		double[] Y0 = new double[] { 150, 0.01 };

		double[] errors = new double[] { 2, 1, 1, 1.667, 1, 1.75, 1.8, 1, 1.429, 1.5, 1.375, 1.714, 1.857, 1.429, 1.2, 1.647, 1.333, 1.5, 2.2, 1.545, 1.286,
				1.273, 1.364, 2, 1, 1.15, 1, 1.375, 1.071, 1.286, 1, 1.25, 1.222, 1, 1.222, 1.2, 1, 1.222, 1, 1.25, 1.083, 1.917, 2.385, 1.944, 1.955, 1.545,
				1.476, 1.273, 2.25, 1.8, 2.5, 1.2, 3.6, 2.25, 1.357, 1.941, 2.7, 1.742, 1.44, 1.5, 1.182, 1.258, 1.227, 1.222, 1.128, 1.214, 1.448, 1.286, 1.5,
				1.25, 1.138, 1.143, 1.174, 1.15, 1.074, 1.069, 1.118, 1.222, 1.167, 1.3, 1.083, 1.261, 1.36, 1.133, 1, 1.15, 1.308, 1.2, 1.053, 1.038, 1.125,
				1, 1, 1.3, 1.2, 1.333, 1.167, 1, 1, 1.111, 1.4, 1.375, 1, 1, 1, 1.111, 1.083, 1.4, 1.375, 1.111, 1.2, 1, 1.125, 1, 1.167, 1.167, 1.1, 1.125,
				1.111, 1.625, 1, 1.091, 1, 1, 1, 1, 1.625, 1.067, 1.222, 1, 1.143, 1.105, 1, 1.737, 1.5, 1.333, 1.143, 1.1, 1.143, 1.083, 1.25, 1 };

		GoelOkumotoModel goelOkumotoMethod = new GoelOkumotoModel(intervals, errors, Y0, eps);

		// When
		double[] Yn = goelOkumotoMethod.calculate();

		// Then
		printResult(calculateSum(errors), Yn[0], Yn[1]);

		DecimalFormat numberFormater = new DecimalFormat("#.##");
		Assert.assertEquals("193.29", numberFormater.format(Yn[0]));
		Assert.assertEquals("0.02", numberFormater.format(Yn[1]));
	}

	private void printResult(double sum, double L, double B) {
		System.out.println("Sum: " + sum + "; L = " + L + "; B = " + B);
	}

	private double[] createIntervals(int intervalNum, int intervalSize) {

		double[] intervals = new double[intervalNum];

		for (int i = 0; i < intervalNum; i++) {
			intervals[i] = i + intervalSize;
		}

		return intervals;
	}

	private double calculateSum(double[] values) {
		double sum = 0;
		for (double value : values) {
			sum += value;
		}

		return sum;
	}
}
