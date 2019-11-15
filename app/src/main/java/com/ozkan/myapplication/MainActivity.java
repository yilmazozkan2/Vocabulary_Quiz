package com.ozkan.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    TextView txtSkor, txtGeriSayac;
    RadioButton rbA, rbB;
    RadioGroup rgroup;
    Random rnd = new Random();
    Context context = this;
    int skor = 0;
    int randomItem = 0;
    int deger = 0;
    Typeface fontum;
    LinearLayout panel;
    String A = "A-) ";
    String B = "B-) ";
    String countDownText;
    CountDownTimer gerisayim;
    ShredPref shredPref = new ShredPref();
    String[] bircok = new String[]{
            "Birçok",
            "Yanlış",
            "Orijinal",
            "Kılavuz",
            "Güneydoğu",
            "Kirpik",
            "Birdenbire",
            "Pek çok",
            "Emretmek",
            "Terk etti",
            "Günaydın",
            "Dil bilimi",
            "Yemekhane",
            "Doğumevi",
            "Çikolata",
            "Kırmızıbiber",
            "Açıkgöz",
            "Grup",
            "Vazgeçti",
            "Hristiyan",
            "Anaokulu",
            "Başsavcı",
            "Parşömen",
            "Makine",
            "Egzoz",
            "Atölye",
            "Hafriyat",
            "Şofben",
            "Ateş böceği",
            "Yüzükoyun",
            "Köpek Balığı",
            "Hafta sonu",
            "Kuru yemiş",
            "Oysaki",
            "Her gün",
            "Herkes",
            "Art arda",
            "Tıraş",
            "Herhangi",
            "Kibrit",
            "Affedersiniz",
            "Hiçbir şey",
            "Dershane",
            "Ateşkes",
            "Laboratuvar",
            "Travma",
            "Babaanne",
            "Uluslararası",
            "Alaca karanlık",
            "Kilitledi",
            "Sürpriz",
            "Kravat",
            "Fark etmek",
            "Şarj",
            "Aslan",
            "Her şey",
            "Birtakım",
            "Çeyiz",
            "Şefkat",
            "Dinozor",
            "Zarafet",
            "Unvan",
            "Gözyaşı",
            "Perhiz",
            "Allah aşkına",
            "Arapçası",
            "arkadaştan",
            "artırmak",
            "ayrım",
            "az çok",
            "baş etmek",
            "ben de kullanıyorum",
            "bir şeye",
            "bir şeyler",
            "bu günlerde",
            "çokbilmiş",
            "fark etmek",
            "gidişgeliş",
            "hâlâ",
            "hammadde",
            "her gün",
            "hukukun",
            "İngilizcenin",
            "işyeri",
            "Lise 1'de bize öğretilen",
            "maşallah",
            "mademki",
            "maalesef",
            "olup da",
            "övünç",
            "rastgele",
            "tabii",
            "tetanos",
            "ya da",
            "zıddı"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        baslangic();
        fontum = Typeface.createFromAsset(getAssets(), "font/times_new_roman.ttf");
        fontdegistir(panel);
        rbA.setChecked(false);
        rbB.setChecked(false);
        countDownTimer();
        txtSkor.setText("Son Skor: " + deger);
    }
    private void countDownTimer() {
        deger = shredPref.degerGöster(context);
        gerisayim = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countDownText = "00:";
                if (millisUntilFinished < 10000){
                    countDownText="00:0";
                }
                txtGeriSayac.setText(countDownText +millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                AlertDialog mesajpenceresi = new AlertDialog.Builder(context)
                        .setTitle("Uyarı!")
                        .setMessage("Oyun bitti. Yeniden başlamak istiyor musunuz?")
                        .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                skor = 0;
                                gerisayim.start();
                            }
                        })
                        .setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            }
        }.start();
    }

    private void baslangic() {
        txtSkor = (TextView) findViewById(R.id.txtSkor);
        txtGeriSayac = (TextView) findViewById(R.id.txtGeriSayac);
        panel = (LinearLayout) findViewById(R.id.panel);
        rgroup = (RadioGroup) findViewById(R.id.rgroup);
        rbA = (RadioButton) findViewById(R.id.rbA);
        rbB = (RadioButton) findViewById(R.id.rbB);
    }

    private void fontdegistir(ViewGroup vgroup) {
        View nesne;
        for (int i = 0; i < vgroup.getChildCount(); i++) {
            nesne = vgroup.getChildAt(i);
            if (nesne instanceof TextView) {
                ((TextView) nesne).setTypeface(fontum);
            }
        }
    }

    private void dogru() {
        skor += 10;
        shredPref.degerEkle(context, skor);
        txtSkor.setText("Skor: " + skor);
    }

    private void yanlis() {
        if (skor > 0) {
            skor -= 5;
            shredPref.degerEkle(context, skor);
            txtSkor.setText("Skor: " + skor);
        }
    }

    public void kont(View v) {
        int radioId = rgroup.getCheckedRadioButtonId();
        RadioButton radiobutton;
        radiobutton = (RadioButton) findViewById(radioId);
        if (radiobutton.getText().toString().equals(A + bircok[randomItem])) {
            dogru();
            radiobutton.setBackgroundColor(Color.GREEN);
        } else if (radiobutton.getText().toString().equals(B + bircok[randomItem])) {
            dogru();
            radiobutton.setBackgroundColor(Color.GREEN);
        } else {
            yanlis();
            radiobutton.setBackgroundColor(Color.RED);
        }
    }

    public void Uret(View v) {
        rgroup.clearCheck();
        rbA.setBackgroundColor(Color.WHITE);
        rbB.setBackgroundColor(Color.WHITE);
        randomItem = rnd.nextInt(bircok.length);
        if (bircok[randomItem].equals("Birçok")) {
            rbA.setText(A + "Birçok");
            rbB.setText(B + "Bir çok");

        } else if (bircok[randomItem].equals("Yanlış")) {
            rbA.setText(A + "Yanlış");
            rbB.setText(B + "Yalnış");

        } else if (bircok[randomItem].equals("Orijinal")) {
            rbA.setText(A + "Orijinal");
            rbB.setText(B + "orjinal");

        } else if (bircok[randomItem].equals("Kılavuz")) {
            rbA.setText(A + "Kılavuz");
            rbB.setText(B + "Klavuz");

        } else if (bircok[randomItem].equals("Güneydoğu")) {
            rbA.setText(A + "Güneydoğu");
            rbB.setText(B + "Güney Doğu");

        } else if (bircok[randomItem].equals("Kirpik")) {
            rbA.setText(A + "Kiprik");
            rbB.setText(B + "Kirpik");
        } else if (bircok[randomItem].equals("Birdenbire")) {
            rbA.setText(A + "Birden bire");
            rbB.setText(B + "Birdenbire");


        } else if (bircok[randomItem].equals("Pek çok")) {
            rbA.setText(A + "Pek çok");
            rbB.setText(B + "Pekçok");

        } else if (bircok[randomItem].equals("Emretmek")) {
            rbA.setText(A + "Emir etmek");
            rbB.setText(B + "Emretmek");


        } else if (bircok[randomItem].equals("Terk etti")) {
            rbA.setText(A + "Terketti");
            rbB.setText(B + "Terk etti");


        } else if (bircok[randomItem].equals("Günaydın")) {
            rbA.setText(A + "Günaydın");
            rbB.setText(B + "Gün aydın");


        } else if (bircok[randomItem].equals("Dilbilimi")) {
            rbA.setText(A + "Dilbilimi");
            rbB.setText(B + "Dil bilimi");


        } else if (bircok[randomItem].equals("Yemekhane")) {
            rbA.setText(A + "Yemekhane");
            rbB.setText(B + "Yemek hane");


        } else if (bircok[randomItem].equals("Doğumevi")) {
            rbA.setText(A + "Doğumevi");
            rbB.setText(B + "Doğum evi");

        } else if (bircok[randomItem].equals("Çikolata")) {
            rbA.setText(A + "Çukulata");
            rbB.setText(B + "Çikolata");


        } else if (bircok[randomItem].equals("Kırmızıbiber")) {
            rbA.setText(A + "Kırmızı biber");
            rbB.setText(B + "Kırmızıbiber");


        } else if (bircok[randomItem].equals("Açıkgöz")) {
            rbA.setText(A + "Açıkgöz");
            rbB.setText(B + "Açık göz");


        } else if (bircok[randomItem].equals("Grup")) {
            rbA.setText(A + "Grup");
            rbB.setText(B + "Gurup");
        } else if (bircok[randomItem].equals("Vazgeçti")) {

            rbA.setText(A + "Vazgeçti");
            rbB.setText(B + "Vaz geçti");

        } else if (bircok[randomItem].equals("Hristiyan")) {
            rbA.setText(A + "Hıristiyan");
            rbB.setText(B + "Hristiyan");

        } else if (bircok[randomItem].equals("Anaokulu")) {

            rbA.setText(A + "Ana okulu");
            rbB.setText(B + "Anaokulu");


        } else if (bircok[randomItem].equals("Başsavcı")) {
            rbA.setText(A + "Başsavcı");
            rbB.setText(B + "Baş savcı");

        } else if (bircok[randomItem].equals("Parşömen")) {

            rbA.setText(A + "Parşömen");
            rbB.setText(B + "Parşümen");


        } else if (bircok[randomItem].equals("Makine")) {

            rbA.setText(A + "Makine");
            rbB.setText(B + "Makina");


        } else if (bircok[randomItem].equals("Egzoz")) {

            rbA.setText(A + "Egsoz");
            rbB.setText(B + "Egzoz");


        } else if (bircok[randomItem].equals("Atölye")) {

            rbA.setText(A + "Atelye");
            rbB.setText(B + "Atölye");


        } else if (bircok[randomItem].equals("Hafriyat")) {

            rbA.setText(A + "Hafriyat");
            rbB.setText(B + "Harfiyat");


        } else if (bircok[randomItem].equals("Şofben")) {

            rbA.setText(A + "Şofben");
            rbB.setText(B + "Şohben");


        } else if (bircok[randomItem].equals("Ateş böceği")) {

            rbA.setText(A + "Ateşböceği");
            rbB.setText(B + "Ateş böceği");


        } else if (bircok[randomItem].equals("Yüzükoyun")) {

            rbA.setText(A + "Yüzü Koyun");
            rbB.setText(B + "Yüzükoyun");


        } else if (bircok[randomItem].equals("Köpek Balığı")) {

            rbA.setText(A + "Köpek Balığı");
            rbB.setText(B + "Köpekbalığı");


        } else if (bircok[randomItem].equals("Hafta sonu")) {

            rbA.setText(A + "Hafta sonu");
            rbB.setText(B + "Haftasonu");


        } else if (bircok[randomItem].equals("Kuru yemiş")) {

            rbA.setText(A + "Kuruyemiş");
            rbB.setText(B + "Kuru yemiş");


        } else if (bircok[randomItem].equals("Oysaki")) {

            rbA.setText(A + "Oysa ki");
            rbB.setText(B + "Oysaki");


        } else if (bircok[randomItem].equals("Her gün")) {

            rbA.setText(A + "Her gün");
            rbB.setText(B + "Hergün");


        } else if (bircok[randomItem].equals("Herkes")) {

            rbA.setText(A + "Herkes");
            rbB.setText(B + "Herkez");


        } else if (bircok[randomItem].equals("Art arda")) {

            rbA.setText(A + "Art arda");
            rbB.setText(B + "Artarda");


        } else if (bircok[randomItem].equals("Tıraş")) {

            rbA.setText(A + "Tıraş");
            rbB.setText(B + "Traş");


        } else if (bircok[randomItem].equals("Herhangi")) {

            rbA.setText(A + "Herhangi");
            rbB.setText(B + "Her hangi");


        } else if (bircok[randomItem].equals("Kibrit")) {

            rbA.setText(A + "Kirbit");
            rbB.setText(B + "Kibrit");


        } else if (bircok[randomItem].equals("Affedersiniz")) {

            rbA.setText(A + "Af edersiniz");
            rbB.setText(B + "Affedersiniz");


        } else if (bircok[randomItem].equals("Hiçbir şey")) {

            rbA.setText(A + "Hiçbir şey");
            rbB.setText(B + "Hiçbirşey");


        } else if (bircok[randomItem].equals("Dershane")) {

            rbA.setText(A + "Dershane");
            rbB.setText(B + "Dersane");


        } else if (bircok[randomItem].equals("Ateşkes")) {

            rbA.setText(A + "Ateş kes");
            rbB.setText(B + "Ateşkes");


        } else if (bircok[randomItem].equals("Laboratuvar")) {

            rbA.setText(A + "Laboratuar");
            rbB.setText(B + "Laboratuvar");


        } else if (bircok[randomItem].equals("Travma")) {

            rbA.setText(A + "Travma");
            rbB.setText(B + "Tıravma");


        } else if (bircok[randomItem].equals("Babaanne")) {

            rbA.setText(A + "Baba anne");
            rbB.setText(B + "Babaanne");


        } else if (bircok[randomItem].equals("Uluslararası")) {

            rbA.setText(A + "Uluslararası");
            rbB.setText(B + "Uluslar arası");


        } else if (bircok[randomItem].equals("Alaca karanlık")) {

            rbA.setText(A + "Alaca karanlık");
            rbB.setText(B + "Alacakaranlık");


        } else if (bircok[randomItem].equals("Kilitledi")) {

            rbA.setText(A + "Kitledi");
            rbB.setText(B + "Kilitledi");


        } else if (bircok[randomItem].equals("Sürpriz")) {

            rbA.setText(A + "Süpriz");
            rbB.setText(B + "Sürpriz");


        } else if (bircok[randomItem].equals("Kravat")) {

            rbA.setText(A + "Kravat");
            rbB.setText(B + "Kıravat");


        } else if (bircok[randomItem].equals("Fark etmek")) {

            rbA.setText(A + "Farketmek");
            rbB.setText(B + "Fark etmek");


        } else if (bircok[randomItem].equals("Şarj")) {

            rbA.setText(A + "Şarj");
            rbB.setText(B + "Şarz");


        } else if (bircok[randomItem].equals("Aslan")) {

            rbA.setText(A + "Aslan");
            rbB.setText(B + "Arslan");

        } else if (bircok[randomItem].equals("Her şey")) {

            rbA.setText(A + "Herşey");
            rbB.setText(B + "Her şey");


        } else if (bircok[randomItem].equals("Birtakım")) {

            rbA.setText(A + "Bir takım");
            rbB.setText(B + "Birtakım");


        } else if (bircok[randomItem].equals("Çeyiz")) {

            rbA.setText(A + "Çeyiz");
            rbB.setText(B + "Ceyiz");


        } else if (bircok[randomItem].equals("Halbuki")) {

            rbA.setText(A + "Halbuki");
            rbB.setText(B + "Halbu ki");

        } else if (bircok[randomItem].equals("Zarafet")) {

            rbA.setText(A + "Zerafet");
            rbB.setText(B + "Zarafet");

        } else if (bircok[randomItem].equals("Dinozor")) {

            rbA.setText(A + "Dinazor");
            rbB.setText(B + "Dinozor");

        } else if (bircok[randomItem].equals("Şefkat")) {

            rbA.setText(A + "Şefkat");
            rbB.setText(B + "Şevkat");

        } else if (bircok[randomItem].equals("Unvan")) {

            rbA.setText(A + "Ünvan");
            rbB.setText(B + "Unvan");

        } else if (bircok[randomItem].equals("Gözyaşı")) {

            rbA.setText(A + "Göz yaşı");
            rbB.setText(B + "Gözyaşı");

        } else if (bircok[randomItem].equals("gidişgeliş")) {

            rbA.setText(A + "gidişgeliş");
            rbB.setText(B + "gidiş geliş");

        } else if (bircok[randomItem].equals("çokbilmiş")) {

            rbA.setText(A + "çok bilmiş");
            rbB.setText(B + "çokbilmiş");

        } else if (bircok[randomItem].equals("fark etmek")) {

            rbA.setText(A + "farketmek");
            rbB.setText(B + "fark etmek");

        } else if (bircok[randomItem].equals("bu günlerde")) {

            rbA.setText(A + "bu günlerde");
            rbB.setText(B + "bugünlerde");

        } else if (bircok[randomItem].equals("bir şeyler")) {

            rbA.setText(A + "birşeyler");
            rbB.setText(B + "bir şeyler");


        } else if (bircok[randomItem].equals("bir şeye")) {

            rbA.setText(A + "bir şeye");
            rbB.setText(B + "birşeye");


        } else if (bircok[randomItem].equals("ben de kullanıyorum")) {

            rbA.setText(A + "bende kullanıyorum");
            rbB.setText(B + "ben de kullanıyorum");

        } else if (bircok[randomItem].equals("baş etmek")) {

            rbA.setText(A + "başetmek");
            rbB.setText(B + "baş etmek");

        } else if (bircok[randomItem].equals("az çok")) {

            rbA.setText(A + "az çok");
            rbB.setText(B + "azçok");


        } else if (bircok[randomItem].equals("ayrım")) {

            rbA.setText(A + "ayrım");
            rbB.setText(B + "ayırım");


        } else if (bircok[randomItem].equals("artırmak")) {

            rbA.setText(A + "artırmak");
            rbB.setText(B + "arttırmak");


        } else if (bircok[randomItem].equals("arkadaştan")) {

            rbA.setText(A + "arkadaştan");
            rbB.setText(B + "arkadaşdan");

        } else if (bircok[randomItem].equals("Arapçası")) {

            rbA.setText(A + "Arapça'sı");
            rbB.setText(B + "Arapçası");

        } else if (bircok[randomItem].equals("Allah aşkına")) {

            rbA.setText(A + "Allahaşkına");
            rbB.setText(B + "Allah aşkına");

        } else if (bircok[randomItem].equals("hâlâ")) {

            rbA.setText(A + "hâlâ");
            rbB.setText(B + "hala");

        } else if (bircok[randomItem].equals("hammadde")) {

            rbA.setText(A + "ham madde");
            rbB.setText(B + "hammadde");

        } else if (bircok[randomItem].equals("her gün")) {

            rbA.setText(A + "her gün");
            rbB.setText(B + "hergün");

        } else if (bircok[randomItem].equals("hukukun")) {

            rbA.setText(A + "hukukun");
            rbB.setText(B + "hukuğun");

        } else if (bircok[randomItem].equals("İngilizcenin")) {

            rbA.setText(A + "İngilizce'nin");
            rbB.setText(B + "İngilizcenin");

        } else if (bircok[randomItem].equals("işyeri")) {

            rbA.setText(A + "işyeri");
            rbB.setText(B + "iş yeri");

        } else if (bircok[randomItem].equals("Lise 1'de bize öğretilen")) {

            rbA.setText(A + "Lise 1 de bize öğretilen");
            rbB.setText(B + "Lise 1'de bize öğretilen");

        } else if (bircok[randomItem].equals("maşallah")) {

            rbA.setText(A + "maaşallah");
            rbB.setText(B + "maşallah");

        } else if (bircok[randomItem].equals("mademki")) {

            rbA.setText(A + "mademki");
            rbB.setText(B + "madem ki");

        } else if (bircok[randomItem].equals("maalesef")) {

            rbA.setText(A + "maalesef");
            rbB.setText(B + "malesef");

        } else if (bircok[randomItem].equals("olup da")) {

            rbA.setText(A + "olup ta");
            rbB.setText(B + "olup da");

        } else if (bircok[randomItem].equals("övünç")) {

            rbA.setText(A + "öğünç");
            rbB.setText(B + "övünç");

        } else if (bircok[randomItem].equals("rastgele")) {

            rbA.setText(A + "rastgele");
            rbB.setText(B + "rasgele");

        } else if (bircok[randomItem].equals("tabii")) {

            rbA.setText(A + "tabii");
            rbB.setText(B + "tabi");

        } else if (bircok[randomItem].equals("tetanos")) {

            rbA.setText(A + "tetenoz");
            rbB.setText(B + "tetanos");

        } else if (bircok[randomItem].equals("ya da")) {

            rbA.setText(A + "ya da");
            rbB.setText(B + "yada");

        } else if (bircok[randomItem].equals("zıddı")) {

            rbA.setText(A + "zıtı");
            rbB.setText(B + "zıddı");

        }
    }
}