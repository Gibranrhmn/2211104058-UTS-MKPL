package lib;

public class TaxFunction {

	private static final int BASIC_NON_TAXABLE = 54000000;
	private static final int MARRIED_ADDITION = 4500000;
	private static final int CHILD_ADDITION = 1500000;

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	
	
	 public static int calculateTax(TaxData data) {
		if (data.numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}
	
		if (data.numberOfChildren > 3) {
			data.numberOfChildren = 3;
		}
	
		int nonTaxableIncome = BASIC_NON_TAXABLE;
		if (data.isMarried) {
			nonTaxableIncome += MARRIED_ADDITION;
		}
		nonTaxableIncome += data.numberOfChildren * CHILD_ADDITION;
		
	
		int netIncome = ((data.monthlySalary + data.otherMonthlyIncome) * data.numberOfMonthWorking) - data.deductible;
		int tax = (int) Math.round(0.05 * (netIncome - nonTaxableIncome));
		
		return Math.max(tax, 0);
	}
	
	
}
