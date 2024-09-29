package com.example.calculatorpythagoras

import android.os.Bundle // Import untuk kelas Bundle, digunakan untuk menyimpan data sementara
import android.view.View // Import untuk kelas View, digunakan untuk komponen UI
import android.widget.Button // Import untuk kelas Button, digunakan untuk tombol UI
import android.widget.EditText // Import untuk kelas EditText, digunakan untuk input teks dari pengguna
import android.widget.TextView // Import untuk kelas TextView, digunakan untuk menampilkan teks di UI
import androidx.appcompat.app.AppCompatActivity // Import untuk kelas AppCompatActivity, kelas dasar untuk Activity di Android

// Mendefinisikan kelas MainActivity yang merupakan turunan dari AppCompatActivity
// dan mengimplementasikan interface View.OnClickListener untuk menangani klik pada View
class MainActivity : AppCompatActivity(), View.OnClickListener {
    // Deklarasi variabel untuk komponen UI menggunakan lateinit, yang berarti
    // variabel ini akan diinisialisasi nanti (setelah findViewById dipanggil)
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    // Deklarasi companion object untuk mendefinisikan konstanta yang digunakan di dalam kelas
    companion object {
        private const val STATE_RESULT = "state_result" // Kunci untuk menyimpan dan mengambil data dari Bundle
    }

    // Fungsi onCreate dijalankan saat Activity pertama kali dibuat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Memanggil fungsi onCreate dari superclass
        setContentView(R.layout.activity_main) // Mengatur layout yang digunakan Activity ini

        // Inisialisasi komponen UI dengan findViewById menggunakan id yang didefinisikan di layout XML
        edtWidth = findViewById(R.id.edt_width) // Menghubungkan variabel edtWidth dengan EditText di layout
        edtHeight = findViewById(R.id.edt_height) // Menghubungkan variabel edtHeight dengan EditText di layout
        edtLength = findViewById(R.id.edt_length) // Menghubungkan variabel edtLength dengan EditText di layout
        btnCalculate = findViewById(R.id.btn_calculate) // Menghubungkan variabel btnCalculate dengan Button di layout
        tvResult = findViewById(R.id.tv_result) // Menghubungkan variabel tvResult dengan TextView di layout

        // Mengatur listener untuk tombol btnCalculate
        btnCalculate.setOnClickListener(this) // Ketika tombol diklik, onClick akan dipanggil

        // Mengecek apakah ada data yang disimpan sebelumnya (ketika orientasi berubah atau Activity di-restart)
        if (savedInstanceState != null) {
            // Mengambil data hasil perhitungan dari state yang disimpan
            val result = savedInstanceState.getString(STATE_RESULT)
            // Menampilkan hasil ke TextView tvResult jika ada data yang disimpan
            tvResult.text = result
        }
    }

    // Metode ini digunakan untuk menyimpan state sebelum Activity dihentikan (misalnya saat orientasi berubah)
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState) // Memanggil onSaveInstanceState dari superclass
        // Menyimpan hasil perhitungan yang ada di tvResult ke dalam Bundle menggunakan kunci STATE_RESULT
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }

    // Implementasi onClick untuk menangani event klik pada View
    override fun onClick(view: View?) {
        // Mengecek apakah view yang diklik adalah btnCalculate berdasarkan id-nya
        if (view?.id == R.id.btn_calculate) {
            // Mengambil input dari EditText dan menghilangkan spasi di awal dan akhir menggunakan .trim()
            val inputLength = edtLength.text.toString().trim() // Mengambil nilai dari edtLength
            val inputWidth = edtWidth.text.toString().trim() // Mengambil nilai dari edtWidth
            val inputHeight = edtHeight.text.toString().trim() // Mengambil nilai dari edtHeight
            var isEmptyFields = false // Inisialisasi variabel boolean untuk memeriksa apakah ada field yang kosong

            // Mengecek apakah inputLength kosong
            if (inputLength.isEmpty()) {
                isEmptyFields = true // Menandai bahwa ada field yang kosong
                edtLength.error = "Field ini tidak boleh kosong" // Menampilkan pesan error di EditText edtLength
            }

            // Mengecek apakah inputWidth kosong
            if (inputWidth.isEmpty()) {
                isEmptyFields = true // Menandai bahwa ada field yang kosong
                edtWidth.error = "Field ini tidak boleh kosong" // Menampilkan pesan error di EditText edtWidth
            }

            // Mengecek apakah inputHeight kosong
            if (inputHeight.isEmpty()) {
                isEmptyFields = true // Menandai bahwa ada field yang kosong
                edtHeight.error = "Field ini tidak boleh kosong" // Menampilkan pesan error di EditText edtHeight
            }

            // Jika semua field sudah terisi (tidak ada field yang kosong)
            if (!isEmptyFields) {
                // Mengonversi input dari String menjadi Double dan menghitung volume
                val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
                // Menampilkan hasil perhitungan volume di TextView tvResult
                tvResult.text = volume.toString()
            }
        }
    }
}
