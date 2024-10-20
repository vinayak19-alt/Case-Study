package tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result result1 = JUnitCore.runClasses(TestCalculateGrossSalaryForEmployee.class);
        for (Failure failure : result1.getFailures())
        {
            System.out.println(failure.toString());
        }
        System.out.println(result1.wasSuccessful());

        Result result2 = JUnitCore.runClasses(TestCalculateNetSalaryAfterDeductions.class);
        for (Failure failure : result2.getFailures())
        {
            System.out.println(failure.toString());
        }
        System.out.println(result2.wasSuccessful());

        Result result3 = JUnitCore.runClasses(TestVerifyTaxCalculationForHighIncomeEmployee.class);
        for (Failure failure : result3.getFailures())
        {
            System.out.println(failure.toString());
        }
        System.out.println(result3.wasSuccessful());

        Result result4 = JUnitCore.runClasses(TestProcessPayrollForMultipleEmployees.class);
        for (Failure failure : result4.getFailures())
        {
            System.out.println(failure.toString());
        }
        System.out.println(result4.wasSuccessful());

        Result result5 = JUnitCore.runClasses(TestVerifyErrorHandlingForInvalidEmployeeData.class);
        for (Failure failure : result5.getFailures())
        {
            System.out.println(failure.toString());
        }
        System.out.println(result5.wasSuccessful());
    }
}
