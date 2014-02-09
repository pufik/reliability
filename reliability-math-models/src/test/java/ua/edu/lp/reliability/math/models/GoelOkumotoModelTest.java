package ua.edu.lp.reliability.math.models;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
		// Project: Chinese researchers results
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
						0.03, 0, 0, 0, 0.03, 0.03, 0, 0, 0, 0, 0, 0 },
				"37.86", // Expected L
				"0.02" // Expected B
		});

		// Project: Apache JackRabbit - all issues
		parameters.add(new Object[] {
				"Apache JackRabbit - all issues",
				109, // interval count
				30, // interval's size
				0.00001, // eps
				new double[] { 5000, 0.01 }, // Y0
				// Errors
				new double[] { 3, 11, 8, 14, 3, 12, 38, 27, 9, 23, 25, 27, 26, 38, 22, 11, 13, 18, 52, 37, 27, 25, 56, 27, 27, 32, 43, 37, 31, 46, 60, 43, 59,
						39, 50, 56, 48, 45, 48, 25, 72, 89, 65, 57, 70, 29, 34, 26, 50, 66, 40, 26, 39, 40, 46, 40, 33, 50, 60, 50, 47, 36, 43, 34, 32, 42, 66,
						32, 23, 25, 21, 44, 30, 37, 24, 30, 16, 33, 31, 17, 28, 29, 27, 27, 26, 45, 29, 33, 28, 21, 37, 17, 27, 41, 33, 16, 16, 19, 18, 16, 19,
						18, 26, 31, 17, 9, 18, 18, 7 }, "3657.87", // Expected L
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
				new double[] { 1, 2.75, 2.667, 3.5, 1, 2, 2.375, 2.25, 1.125, 2.091, 2.083, 2.25, 1.733, 2.923, 1.571, 1.571, 1.444, 1.5, 3.467, 1.762, 2.077,
						2.083, 3.294, 1.8, 1.929, 1.684, 2.15, 2.312, 2.385, 2, 2.5, 1.654, 2.269, 1.95, 2.5, 2.074, 2.182, 1.731, 3, 1.923, 2.057, 2.472, 2.5,
						2.375, 2.8, 1.45, 1.545, 1.733, 2.273, 2.75, 2.667, 2, 1.625, 2.353, 2.875, 3.077, 2.75, 2.941, 3.333, 2.941, 2.238, 1.714, 1.87,
						1.619, 3.2, 2.333, 2.87, 1.882, 1.533, 1.562, 1.312, 2.2, 2.727, 2.056, 1.5, 3, 1.455, 1.833, 1.722, 2.429, 2, 1.611, 1.688, 2.25,
						2.364, 2.5, 2.071, 1.737, 2, 1.312, 2.056, 1.417, 2.077, 1.952, 1.941, 1.143, 1.6, 1.118, 1.5, 1.6, 1.9, 1.385, 1.444, 1.938, 1.545,
						2.25, 1.385, 2, 1, }, "224.94", // Expected L
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
						21, 28, 24, 11, 17, 17, 4 }, "4244.68", // Expected L
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
				new double[] { 2, 1, 1, 1.667, 1, 1.75, 1.8, 1, 1.429, 1.5, 1.375, 1.714, 1.857, 1.429, 1.2, 1.647, 1.333, 1.5, 2.2, 1.545, 1.286, 1.273,
						1.364, 2, 1, 1.15, 1, 1.375, 1.071, 1.286, 1, 1.25, 1.222, 1, 1.222, 1.2, 1, 1.222, 1, 1.25, 1.083, 1.917, 2.385, 1.944, 1.955, 1.545,
						1.476, 1.273, 2.25, 1.8, 2.5, 1.2, 3.6, 2.25, 1.357, 1.941, 2.7, 1.742, 1.44, 1.5, 1.182, 1.258, 1.227, 1.222, 1.128, 1.214, 1.448,
						1.286, 1.5, 1.25, 1.138, 1.143, 1.174, 1.15, 1.074, 1.069, 1.118, 1.222, 1.167, 1.3, 1.083, 1.261, 1.36, 1.133, 1, 1.15, 1.308, 1.2,
						1.053, 1.038, 1.125, 1, 1, 1.3, 1.2, 1.333, 1.167, 1, 1, 1.111, 1.4, 1.375, 1, 1, 1, 1.111, 1.083, 1.4, 1.375, 1.111, 1.2, 1, 1.125, 1,
						1.167, 1.167, 1.1, 1.125, 1.111, 1.625, 1, 1.091, 1, 1, 1, 1, 1.625, 1.067, 1.222, 1, 1.143, 1.105, 1, 1.737, 1.5, 1.333, 1.143, 1.1,
						1.143, 1.083, 1.25, 1 }, "193.29", // Expected L
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
						4, 11, 12, 6, 2, 1 }, "1949.06", // Expected L
				"0.1" // Expected B
		});

		// Project: Apache Shindig - issue per reporter
		parameters.add(new Object[] {
				"Apache Shindig - issue per reporter",
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

		// Project: Apache Camel - all issues
		parameters.add(new Object[] {
				"Apache Camel - all issues",
				83, // interval count
				30, // interval's size
				0.00001, // eps
				new double[] { 6000, 0.1 }, // Y0
				// Errors
				new double[] { 1, 1, 57, 21, 52, 29, 36, 54, 30, 32, 36, 66, 70, 79, 88, 120, 97, 79, 91, 91, 78, 95, 103, 96, 72, 87, 111, 93, 97, 83, 75,
						116, 85, 103, 77, 97, 84, 81, 113, 133, 76, 90, 114, 83, 97, 127, 124, 96, 95, 113, 134, 117, 116, 104, 94, 127, 115, 116, 92, 79, 99,
						87, 93, 65, 76, 112, 94, 76, 79, 107, 86, 114, 100, 83, 88, 97, 97, 109, 118, 105, 75, 58, 23 }, "7159.1", // Expected L
				"0.1" // Expected B
		});

		// Project: Apache Camel - issue per reporter
		parameters.add(new Object[] {
				"Apache Camel - issue per reporter",
				83, // interval count
				30, // interval's size
				0.00001, // eps
				new double[] { 500, 0.001 }, // Y0
				// Errors
				new double[] { 1, 1, 14.25, 4.2, 5.2, 3.625, 3.273, 4.909, 2.143, 2.286, 3, 3.667, 4.375, 3.435, 5.176, 5.714, 4.409, 3.95, 3.957, 3.25, 3.545,
						4.318, 5.421, 3.84, 4, 3.107, 3.7, 3.321, 4.042, 3.458, 3.947, 3.515, 4.048, 3.552, 3.85, 3.345, 3.5, 2.893, 4.185, 4.75, 3.304, 3.103,
						2.78, 2.306, 3.129, 3.098, 2.952, 2.341, 2.879, 2.756, 2.978, 2.543, 2.698, 2.419, 2.089, 3.175, 4.107, 2.762, 2, 2.324, 2.357, 2.071,
						2.214, 1.548, 2.452, 2.872, 2.541, 2, 2.633, 2.326, 1.792, 2.192, 2.381, 2.306, 1.725, 2.064, 2.425, 2.37, 2.314, 2.386, 1.829, 1.657,
						2.091 }, "265.45", // Expected L
				"0.25" // Expected B
		});

		// Project: Apache CXF - all issues
		parameters.add(new Object[] {
				"Apache CXF - all issues",
				93, // interval count
				30, // interval's size
				0.00001, // eps
				new double[] { 4000, 0.1 }, // Y0
				// Errors
				new double[] { 1, 1, 13, 119, 56, 86, 66, 57, 42, 69, 111, 81, 60, 109, 96, 119, 77, 83, 102, 51, 47, 37, 66, 58, 57, 54, 45, 60, 54, 36, 28,
						48, 56, 65, 46, 47, 73, 46, 39, 36, 56, 53, 30, 42, 45, 61, 47, 40, 29, 58, 42, 45, 91, 55, 67, 74, 67, 65, 47, 81, 66, 69, 86, 55, 53,
						53, 58, 75, 71, 68, 59, 75, 48, 46, 41, 37, 79, 59, 59, 72, 63, 71, 58, 60, 48, 68, 79, 65, 57, 60, 50, 54, 18

				}, "5472.03", // Expected L
				"0.1" // Expected B
		});

		// Project: Apache CXF - issue per reporter
		parameters.add(new Object[] {
				"Apache CXF - issue per reporter",
				93, // interval count
				30, // interval's size
				0.00001, // eps
				new double[] { 500, 0.01 }, // Y0
				// Errors
				new double[] { 1, 1, 2.167, 6.611, 2.8, 4.3, 3.3, 3.167, 2.471, 3.45, 4.625, 2.793, 3.158, 3.028, 2.133, 3.216, 2.484, 2.594, 3, 2.318, 1.469,
						1.194, 1.737, 1.871, 1.462, 1.459, 1.5, 1.622, 1.421, 1.241, 1.217, 1.655, 1.366, 1.711, 1.278, 1.27, 1.872, 1.704, 1.182, 1.161,
						1.806, 1.559, 1.25, 1.2, 1.452, 1.419, 1.306, 1.333, 1.526, 2.148, 1.556, 1.552, 2.844, 1.964, 1.811, 2, 1.489, 2.097, 1.567, 1.653,
						2.276, 1.605, 1.72, 1.774, 1.472, 2.12, 2.148, 1.829, 1.868, 2, 1.553, 2.083, 1.778, 1.704, 1.464, 1.423, 1.881, 1.553, 1.735, 1.636,
						1.575, 1.511, 1.568, 2, 1.5, 1.744, 1.612, 1.806, 1.676, 1.622, 1.667, 1.5, 1.385

				}, "179.78", // Expected L
				"0.05" // Expected B
		});

		// Project: Apache Open EJB - all issues
		parameters.add(new Object[] {
				"Apache Open EJB - all issues",
				116, // interval count
				30, // interval's size
				0.00001, // eps
				new double[] { 3000, 0.01 }, // Y0
				// Errors
				new double[] { 1, 1, 2, 1, 1, 4, 3, 2, 1, 1, 1, 3, 2, 10, 13, 10, 15, 4, 2, 2, 60, 1, 7, 64, 5, 44, 13, 25, 83, 39, 33, 15, 34, 18, 16, 13, 18,
						51, 24, 14, 15, 16, 7, 9, 25, 10, 17, 26, 29, 20, 11, 31, 13, 18, 16, 4, 11, 10, 15, 9, 8, 11, 12, 19, 15, 12, 106, 7, 12, 18, 18, 20,
						12, 30, 15, 27, 19, 10, 7, 10, 72, 25, 31, 56, 14, 14, 27, 8, 10, 19, 28, 26, 25, 17, 5, 18, 26, 16, 7, 23, 32, 21, 15, 9, 8, 5, 13, 2,
						3, 2, 2, 7, 11, 8, 4, 4 }, "1979.73", // Expected L
				"0.04" // Expected B
		});

		// Project: Apache Open EJB - issue per reporter
		parameters.add(new Object[] {
				"Apache Open EJB - issue per reporter",
				116, // interval count
				30, // interval's size
				0.00001, // eps
				new double[] { 700, 0.01 }, // Y0
				// Errors
				new double[] { 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1.5, 1, 3.333, 6.5, 5, 5, 2, 1, 1, 20, 1, 1.75, 32, 2.5, 11, 2.6, 3.571, 8.3, 3.545, 4.125,
						1.667, 5.667, 2.25, 2, 1.857, 3, 12.75, 4, 2.333, 3.75, 5.333, 1.75, 3, 6.25, 2.5, 8.5, 5.2, 3.625, 2.857, 2.75, 5.167, 1.3, 1.5,
						2.667, 1.333, 1.833, 1.429, 5, 1.8, 1.333, 1.833, 1.714, 3.8, 2.5, 1.714, 26.5, 1.75, 1.714, 2, 2.25, 2.5, 2, 3, 2.143, 2.455, 2.375,
						1.429, 1.75, 1.25, 9, 3.571, 5.167, 5.6, 1.75, 2.333, 6.75, 1.6, 1.429, 3.167, 9.333, 6.5, 12.5, 4.25, 5, 3, 4.333, 5.333, 3.5, 5.75,
						4, 2.333, 2.5, 2.25, 1.333, 1, 3.25, 1, 1, 1, 2, 2.333, 2.75, 2.667, 1.333, 1.333 }, "460.61", // Expected L
				"0.02" // Expected B
		});

		// Project: Apache Karaf - all issues
		parameters.add(new Object[] {
				"Apache Karaf - all issues",
				57, // interval count
				30, // interval's size
				0.00001, // eps
				new double[] { 4800, 0.01 }, // Y0
				// Errors
				new double[] { 27, 1, 1, 4, 1, 8, 3, 2, 3, 6, 1, 12, 26, 43, 43, 58, 21, 44, 45, 79, 63, 60, 52, 59, 32, 104, 43, 67, 64, 93, 68, 41, 68, 56,
						128, 87, 85, 98, 90, 95, 105, 55, 49, 62, 59, 44, 37, 51, 24, 52, 36, 33, 51, 52, 67, 84, 5

				}, "2747.67", // Expected L
				"0.1" // Expected B
		});

		// Project: Apache Karaf - issue per reporter
		parameters.add(new Object[] {
				"Apache Karaf - issue per reporter",
				57, // interval count
				30, // interval's size
				0.00001, // eps
				new double[] { 300, 0.01 }, // Y0
				// Errors
				new double[] { 9, 1, 1, 2, 1, 2, 1, 1, 1, 1.5, 1, 1.714, 2.6, 5.375, 3.583, 3.222, 1.615, 2.75, 2.647, 4.158, 3.316, 3.529, 2.737, 2.95, 2.133,
						4.522, 2.389, 4.467, 3.556, 3.444, 4, 2.05, 2.957, 3.733, 4.129, 3, 3.696, 5.158, 3.6, 3.654, 4.038, 2.115, 2.722, 2.296, 2.95, 2.316,
						2.846, 3, 1.5, 2.6, 2.571, 2.357, 2.429, 3.714, 3.526, 3.652, 1.667

				}, "164.48", // Expected L
				"0.13" // Expected B
		});

		// Project: Apache James Mail Server - all issues
		parameters.add(new Object[] {
				"Apache James Mail Server - all issues",
				148, // interval count
				30, // interval's size
				0.00001, // eps
				new double[] { 2250, 0.01 }, // Y0
				// Errors
				new double[] { 4, 1, 1, 5, 3, 3, 4, 3, 6, 3, 5, 3, 6, 6, 4, 6, 4, 8, 3, 11, 11, 3, 3, 4, 9, 4, 2, 13, 4, 3, 1, 10, 81, 34, 18, 7, 10, 5, 4, 6,
						9, 3, 10, 9, 7, 10, 4, 3, 29, 8, 5, 2, 9, 6, 18, 18, 31, 31, 32, 18, 45, 19, 40, 27, 17, 3, 6, 7, 2, 1, 4, 6, 7, 5, 3, 1, 2, 4, 1, 10,
						3, 3, 4, 1, 2, 2, 2, 7, 5, 3, 9, 3, 1, 2, 11, 10, 3, 2, 24, 12, 8, 9, 17, 12, 9, 6, 15, 44, 45, 26, 17, 16, 17, 19, 22, 27, 6, 18, 14,
						21, 7, 8, 10, 18, 14, 10, 11, 9, 5, 7, 2, 3, 1, 11, 10, 10, 15, 7, 4, 5, 3, 3, 3, 3, 6, 3, 6, 1 }, 
				"1508.26", // Expected L
				"0.02" // Expected B
		});

		// Project: Apache James Mail Server - issue per reporter
		parameters.add(new Object[] {
				"Apache James Mail Server - issue per reporter",
				148, // interval count
				30, // interval's size
				0.00001, // eps
				new double[] { 600, 0.01 }, // Y0
				// Errors
				new double[] { 1, 1, 1, 1.667, 3, 1, 1, 1, 1.5, 1, 1.25, 1, 3, 1.2, 1.333, 1.5, 1.333, 2, 1, 1.571, 1.1, 1.5, 1, 1, 1.125, 1, 1, 2.6, 1, 1, 1,
						1.667, 10.125, 2.267, 1.8, 1.167, 1.429, 1, 1, 1, 1.8, 1, 2.5, 3, 1.75, 2.5, 1.333, 3, 7.25, 2, 1.25, 1, 2.25, 3, 3, 6, 7.75, 5.167,
						6.4, 3, 5, 1.9, 4.444, 2.25, 2.125, 1, 1, 1.75, 1, 1, 1.333, 1.2, 2.333, 1.667, 1, 1, 2, 1.333, 1, 3.333, 1, 1, 1.333, 1, 1, 1, 2, 7,
						2.5, 1, 3, 1, 1, 1, 5.5, 2.5, 1.5, 1, 3.429, 12, 4, 2.25, 2.429, 1.714, 2.25, 3, 7.5, 11, 5, 5.2, 3.4, 3.2, 2.429, 2.111, 3.667, 2.25,
						1.2, 3.6, 2, 4.2, 2.333, 1.333, 3.333, 3, 3.5, 1.667, 2.2, 1.125, 1, 1.4, 1, 1, 1, 1.833, 2, 1.667, 1.875, 1.75, 1, 2.5, 1, 1, 1, 1,
						1.2, 1, 1.2, 1

				},
				"363.26", // Expected L
				"0.01" // Expected B
		});

		return parameters;
	}

	@Test
	public void testCalculate() {
		// Given
		double[] intervals = createIntervals(intervalNum, intervalSize);

		// When
		GoelOkumotoModel goelOkumotoMethod = new GoelOkumotoModel(intervals, errors, new double[] { Y0[0], Y0[1] }, eps);

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
		System.out.println("Y0: [" + Y0[0] + ", " + Y0[1] + "]");
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
