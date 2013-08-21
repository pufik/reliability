package ua.edu.lp.reliability.math.models;

import java.text.DecimalFormat;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for reliability prediction model by Gole-Okumoto
 * 
 * @author iurii
 * 
 */
public class GoelOkumotoModelTest {

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

	private GoelOkumotoModel goelOkumotoMethod;

	@Before
	public void init() {
		double[] intervals = new double[120];

		for (int i = 0; i < intervalNum; i++) {
			intervals[i] = i + intervalSize;
		}

		goelOkumotoMethod = new GoelOkumotoModel(intervals, errors, Y0, eps);
	}

	@Test
	public void testCalculate() {
		double[] Yn = goelOkumotoMethod.calculate();
		
		DecimalFormat numberFormater = new DecimalFormat("#.##");
		System.out.println("L = " + Yn[0] + "; B = " + Yn[1]);
		Assert.assertEquals("37.86", numberFormater.format(Yn[0]));
		Assert.assertEquals("0.02", numberFormater.format(Yn[1]));
	}
}
