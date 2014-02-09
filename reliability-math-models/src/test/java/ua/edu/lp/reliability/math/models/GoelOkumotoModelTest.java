package ua.edu.lp.reliability.math.models;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Test for reliability prediction model by Gole-Okumoto.
 * 
 * @author Iurii Parfeniuk
 * 
 */
@RunWith(Parameterized.class)
public class GoelOkumotoModelTest {
	private String projectName;
	private int intervalNum;
	private int intervalSize;
	private double eps;
	private double[] Y0;
	private double[] errors;
	private String expectedL;
	private String expectedB;

	public GoelOkumotoModelTest(String projectName, int intervalNum, int intervalSize, double eps, double[] Y0, double[] errors, String L, String B) {
		this.projectName = projectName;
		this.intervalNum = intervalNum;
		this.intervalSize = intervalSize;
		this.eps = eps;
		this.Y0 = Y0;
		this.errors = errors;
		this.expectedL = L;
		this.expectedB = B;
	}

	@Parameters
	public static Collection<Object[]> initParameters() {
		List<Object[]> parameters = new ArrayList<>();
		// Project: China researchers results
		parameters.add(new Object[] {
				"Chinees research project",
				120, // interval count
				10, // interval's size
				0.00001, // eps
				new double[] { 20, 0.01 }, // Y0
				// Errors
				new double[] { 9.40, 6.43, 3.95, 2.48, 1.43, 1.15, 1.08, 0.93, 0.90, 0.63, 0.60, 0.48, 0.45, 0.40, 0.28, 0.35, 0.28, 0.28, 0.15, 0.18, 0.10,
						0.15, 0.15, 0.18, 0.08, 0.08, 0.05, 0.18, 0.13, 0.13, 0.08, 0.03, 0.05, 0.05, 0.05, 0, 0, 0, 0.03, 0.08, 0.03, 0, 0.05, 0.05, 0.03,
						0.08, 0.03, 0.05, 0, 0, 0, 0.05, 0.03, 0, 0, 0.05, 0, 0.08, 0.03, 0.03, 0.03, 0.03, 0.03, 0, 0.05, 0, 0.03, 0.03, 0, 0, 0.03, 0.05, 0,
						0, 0, 0, 0, 0.03, 0, 0, 0, 0, 0.03, 0, 0, 0, 0.03, 0, 0, 0.03, 0, 0.03, 0, 0.05, 0, 0, 0, 0, 0.03, 0, 0, 0, 0.03, 0, 0, 0, 0, 0.03,
						0.03, 0, 0, 0, 0.03, 0.03, 0, 0, 0, 0, 0, 0, }, 
				"37.86", //Expected L
				"0.02" //Expected B
				});
		
		// Project: Apache JackRabbit - all issues
		parameters.add(new Object[] {
				"Apache JackRabbit - all issues",
				109, // interval count
				30, // interval's size
				0.00001, // eps
				new double[] { 5000, 0.01 }, // Y0
				// Errors
				new double[] { 3, 11, 8, 14, 3, 12, 38, 27, 9, 23, 25, 27, 26, 38, 22, 11, 13, 18, 52, 37, 27, 25, 56, 27, 27, 32, 43, 37, 31, 46,
						60, 43, 59, 39, 50, 56, 48, 45, 48, 25, 72, 89, 65, 57, 70, 29, 34, 26, 50, 66, 40, 26, 39, 40, 46, 40, 33, 50, 60, 50, 47, 36, 43, 34, 32, 42,
						66, 32, 23, 25, 21, 44, 30, 37, 24, 30, 16, 33, 31, 17, 28, 29, 27, 27, 26, 45, 29, 33, 28, 21, 37, 17, 27, 41, 33, 16, 16, 19, 18, 16, 19, 18,
						26, 31, 17, 9, 18, 18, 7 },
				"3657.87", // Expected L
				"0.03" // Expected B
		});
		
		// Project: Apache JackRabbit - issue per reporter
		parameters.add(new Object[] {
				"Apache JackRabbit - bug per reporter",
				109, // interval count
				30, // interval's size
				0.00001, // eps
				new double[] { 200, 0.01 }, // Y0
				// Errors
				new double[] { 1, 2.75, 2.667, 3.5, 1, 2, 2.375, 2.25, 1.125, 2.091, 2.083, 2.25, 1.733, 2.923, 1.571, 1.571, 1.444, 1.5, 3.467,
						1.762, 2.077, 2.083, 3.294, 1.8, 1.929, 1.684, 2.15, 2.312, 2.385, 2, 2.5, 1.654, 2.269, 1.95, 2.5, 2.074, 2.182, 1.731, 3, 1.923, 2.057,
						2.472, 2.5, 2.375, 2.8, 1.45, 1.545, 1.733, 2.273, 2.75, 2.667, 2, 1.625, 2.353, 2.875, 3.077, 2.75, 2.941, 3.333, 2.941, 2.238, 1.714, 1.87,
						1.619, 3.2, 2.333, 2.87, 1.882, 1.533, 1.562, 1.312, 2.2, 2.727, 2.056, 1.5, 3, 1.455, 1.833, 1.722, 2.429, 2, 1.611, 1.688, 2.25, 2.364, 2.5,
						2.071, 1.737, 2, 1.312, 2.056, 1.417, 2.077, 1.952, 1.941, 1.143, 1.6, 1.118, 1.5, 1.6, 1.9, 1.385, 1.444, 1.938, 1.545, 2.25, 1.385, 2, 1,
				},
				"224.94", // Expected L
				"0.07" // Expected B
		});

		// Project: Apache Struts 2 - all issues
		parameters.add(new Object[] {
				"Apache Struts 2 - issue issues",
				142, // interval count
				30, // interval's size
				0.00001, // eps
				new double[] { 6000, 0.01 }, // Y0
				// Errors
				new double[] { 34, 12, 1, 9, 11, 9, 12, 7, 16, 9, 22, 21, 35, 17, 16, 45, 43, 25, 50, 28, 15, 29, 24, 18, 12, 39, 24, 14, 26, 17, 18, 22, 19,
						14, 14, 20, 6, 13, 12, 9, 17, 90, 46, 79, 73, 84, 56, 25, 25, 36, 35, 26, 33, 25, 45, 59, 102, 87, 84, 36, 59, 61, 56, 72, 70, 58, 61,
						57, 66, 56, 45, 53, 40, 38, 43, 48, 30, 35, 42, 43, 34, 41, 51, 46, 28, 31, 36, 21, 36, 44, 34, 18, 17, 19, 26, 18, 9, 10, 10, 17, 19,
						16, 12, 15, 16, 14, 21, 16, 20, 19, 9, 7, 14, 8, 19, 19, 17, 18, 19, 20, 24, 13, 12, 14, 12, 28, 20, 24, 20, 15, 45, 28, 24, 46, 29,
						21, 28, 24, 11, 17, 17, 4 },
				"4244.68", // Expected L
				"0.03" // Expected B
		});
		
		// Project: Apache Struts 2 - issue per reporter
		parameters.add(new Object[] {
				"Apache Struts 2 - issue per reporter",
				142, // interval count
				30, // interval's size
				0.00001, // eps
				new double[] { 150, 0.01 }, // Y0
				// Errors
				new double[] { 2, 1, 1, 1.667, 1, 1.75, 1.8, 1, 1.429, 1.5, 1.375, 1.714, 1.857, 1.429, 1.2, 1.647, 1.333, 1.5, 2.2, 1.545, 1.286,
						1.273, 1.364, 2, 1, 1.15, 1, 1.375, 1.071, 1.286, 1, 1.25, 1.222, 1, 1.222, 1.2, 1, 1.222, 1, 1.25, 1.083, 1.917, 2.385, 1.944, 1.955, 1.545,
						1.476, 1.273, 2.25, 1.8, 2.5, 1.2, 3.6, 2.25, 1.357, 1.941, 2.7, 1.742, 1.44, 1.5, 1.182, 1.258, 1.227, 1.222, 1.128, 1.214, 1.448, 1.286, 1.5,
						1.25, 1.138, 1.143, 1.174, 1.15, 1.074, 1.069, 1.118, 1.222, 1.167, 1.3, 1.083, 1.261, 1.36, 1.133, 1, 1.15, 1.308, 1.2, 1.053, 1.038, 1.125,
						1, 1, 1.3, 1.2, 1.333, 1.167, 1, 1, 1.111, 1.4, 1.375, 1, 1, 1, 1.111, 1.083, 1.4, 1.375, 1.111, 1.2, 1, 1.125, 1, 1.167, 1.167, 1.1, 1.125,
						1.111, 1.625, 1, 1.091, 1, 1, 1, 1, 1.625, 1.067, 1.222, 1, 1.143, 1.105, 1, 1.737, 1.5, 1.333, 1.143, 1.1, 1.143, 1.083, 1.25, 1 },
				"193.29", // Expected L
				"0.02" // Expected B
		});
		
		// Project: Apache Shindig - all issues
		parameters.add(new Object[] {
				"Apache Shindig - all issues",
				75, // interval count
				30, // interval's size
				0.00001, // eps
				new double[] { 2500, 0.1 }, // Y0
				// Errors
				new double[] { 1, 41, 58, 66, 52, 104, 91, 73, 70, 76, 36, 47, 98, 81, 60, 45, 40, 30, 31, 31, 26, 21, 33, 22, 14, 25, 23, 12, 10, 24, 30, 25,
						14, 22, 20, 21, 7, 13, 15, 8, 12, 10, 12, 5, 53, 21, 21, 9, 15, 16, 21, 28, 20, 22, 28, 20, 19, 11, 3, 7, 4, 9, 10, 5, 1, 4, 1, 2, 8,
						4, 11, 12, 6, 2, 1
				},
				"1949.06", // Expected L
				"0.1" // Expected B
		});

		// Project: Apache Shindig - issue per reporter
		parameters.add(new Object[] {
				"Apache Shindig - bug per reporter",
				75, // interval count
				30, // interval's size
				0.00001, // eps
				new double[] { 200, 0.01 }, // Y0
				// Errors
				new double[] { 1, 1.952, 2.762, 2.75, 2.08, 3.714, 3.25, 2.704, 3.043, 2.621, 1.714, 1.88, 4.261, 2.893, 2.308, 2.143, 1.818, 1.5, 2.214,
						1.938, 1.529, 1.909, 2.062, 1.375, 1.4, 1.786, 1.643, 1.714, 1.667, 2, 1.765, 2.273, 1.077, 1.294, 1.333, 1.75, 1.4, 1.3, 1.5, 1.143,
						1.333, 1.25, 1.5, 1, 3.118, 1.4, 2.625, 1.125, 1.875, 1.455, 1.909, 2, 1.538, 1.571, 2.154, 2.222, 2.111, 1.222, 1, 1.4, 4, 1.5, 2,
						1.667, 1, 1.333, 1, 1, 1.6, 2, 2.75, 3, 2, 1, 1

				}, "141.13", // Expected L
				"0.09" // Expected B
		});
		

		return parameters;
	}

	@Test
	public void testCalculate() {
		// Given
		double[] intervals = createIntervals(intervalNum, intervalSize);

		// When
		GoelOkumotoModel goelOkumotoMethod = new GoelOkumotoModel(intervals, errors, new double[]{Y0[0], Y0[1]}, eps);

		double[] Yn = goelOkumotoMethod.calculate();

		// Then
		printResult(calculateSum(errors), Yn[0], Yn[1]);

		DecimalFormat numberFormater = new DecimalFormat("#.##");
		Assert.assertEquals(expectedL, numberFormater.format(Yn[0]));
		Assert.assertEquals(expectedB, numberFormater.format(Yn[1]));
	}

	private void printResult(double sum, double L, double B) {
		System.out.println("--------------------------");
		System.out.println("Project: " + projectName + ";");
		System.out.println("Interval: count = " + intervalNum + ", size = " + intervalSize + ";");
		System.out.println("Y0: [" + Y0[0] + ", " + Y0[1]+"]");
		System.out.println("Errors: \n" + Arrays.toString(errors));
		System.out.println("Sum: " + sum + "; L = " + L + "; B = " + B + " ]");
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
