## Tugas Kecil 1 | Strategi Algoritma (IF2211)

Tugas Kecil 1 untuk mata kuliah Strategi Algoritma (IF2211) berupa IQ Puzzler Pro solver. Permainanan papan IQ Puzzler Pro dengan diberikan papan awal kosong dan beberapa blok diselesaikan dengan algoritma brute force murni tanpa heuristik, yaitu dengan mencoba semua permutasi dari balok pada papan hingga mendapatkan solusi (jika ada).

Dibuat oleh Timothy Niels Ruslim (10123053).

### Struktur Program

Digunakan bahasa Java. Maka, diperlukan compiler Java dengan JDK (Java Development Kit) perlu terinstall. Untuk menggunakan program dapat dikompilasi menjadi executable java dengan menjalankan `javac App.java` pada CL pada directory `../src`. Lalu, executable dapat dijalankan dengan command `java App.java`. Untuk memudahkan, folder pada repository dapat dibuka menggunakan IDE seperti Visual Studio Code, Netbeans, atau Eclipse sebagai project, lalu file `App.java` dapat di-run secara langsung.

- `src`: folder untuk source
- `bin`: folder untuk executables
- `test`: folder untuk file test case dan solusinya dalam `.txt`
- `doc`: folder untuk laporan dalam `.pdf`

### Cara Kerja Program

Solver ini berupa program CLI. Berikut cara program menerima input puzzle.

1. Sesuai prompt, isi absolute atau relative path dari file `.txt` yang sesuai.
2. Jika `.txt` sudah berada di folder `test`, cukup tulis nama filenya.
3. Perhatikan agar `.txt` sudah memiliki format seperti pada spesifikasi Tucil.

Berikut cara program menghasilkan output.

1. Puzzle akan diselesaikan dengan algoritma brute-force (akan diperlukan waktu tergantung kesulitan puzzle).
2. Jika ada solusi, program akan menghasilkan output berupa papan dan letak setiap blok pada papan yang sudah diwarnakan secara unik.
3. Ditampilkan juga waktu yang dimakan dan kasus yang ditelusuri oleh algoritma.
4. Dengan mengikuti prompt terakhir, output tersebut dapat disimpan dalam suatu `.txt` pada folder `test`. File solusi akan memiliki nama sama dengan file input tetapi dengan sufiks `_Solution.txt`.

Untuk penjelasan cara kerja algoritma lebih spesifik secara mendalam, dapat dilihat pada laporan berupa `.pdf` di folder `doc`.
