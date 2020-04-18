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
        if (radiobutton.getText().toString().equals(bircok[randomItem])) {
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
            rbA.setText("Birçok");
            rbB.setText("Bir çok");

        } else if (bircok[randomItem].equals("Yanlış")) {
            rbA.setText("Yanlış");
            rbB.setText("Yalnış");

        } else if (bircok[randomItem].equals("Orijinal")) {
            rbA.setText("Orijinal");
            rbB.setText("orjinal");

        } else if (bircok[randomItem].equals("Kılavuz")) {
            rbA.setText("Kılavuz");
            rbB.setText("Klavuz");

        } else if (bircok[randomItem].equals("Güneydoğu")) {
            rbA.setText("Güneydoğu");
            rbB.setText("Güney Doğu");

        } else if (bircok[randomItem].equals("Kirpik")) {
            rbA.setText("Kiprik");
            rbB.setText("Kirpik");
        } else if (bircok[randomItem].equals("Birdenbire")) {
            rbA.setText("Birden bire");
            rbB.setText("Birdenbire");


        } else if (bircok[randomItem].equals("Pek çok")) {
            rbA.setText("Pek çok");
            rbB.setText("Pekçok");

        } else if (bircok[randomItem].equals("Emretmek")) {
            rbA.setText("Emir etmek");
            rbB.setText("Emretmek");


        } else if (bircok[randomItem].equals("Terk etti")) {
            rbA.setText("Terketti");
            rbB.setText("Terk etti");


        } else if (bircok[randomItem].equals("Günaydın")) {
            rbA.setText("Günaydın");
            rbB.setText("Gün aydın");


        } else if (bircok[randomItem].equals("Dilbilimi")) {
            rbA.setText("Dilbilimi");
            rbB.setText("Dil bilimi");


        } else if (bircok[randomItem].equals("Yemekhane")) {
            rbA.setText("Yemekhane");
            rbB.setText("Yemek hane");


        } else if (bircok[randomItem].equals("Doğumevi")) {
            rbA.setText("Doğumevi");
            rbB.setText("Doğum evi");

        } else if (bircok[randomItem].equals("Çikolata")) {
            rbA.setText("Çukulata");
            rbB.setText("Çikolata");


        } else if (bircok[randomItem].equals("Kırmızıbiber")) {
            rbA.setText("Kırmızı biber");
            rbB.setText("Kırmızıbiber");


        } else if (bircok[randomItem].equals("Açıkgöz")) {
            rbA.setText("Açıkgöz");
            rbB.setText("Açık göz");


        } else if (bircok[randomItem].equals("Grup")) {
            rbA.setText("Grup");
            rbB.setText("Gurup");
        } else if (bircok[randomItem].equals("Vazgeçti")) {

            rbA.setText( "Vazgeçti");
            rbB.setText( "Vaz geçti");

        } else if (bircok[randomItem].equals("Hristiyan")) {
            rbA.setText( "Hıristiyan");
            rbB.setText("Hristiyan");

        } else if (bircok[randomItem].equals("Anaokulu")) {

            rbA.setText( "Ana okulu");
            rbB.setText( "Anaokulu");


        } else if (bircok[randomItem].equals("Başsavcı")) {
            rbA.setText( "Başsavcı");
            rbB.setText("Baş savcı");

        } else if (bircok[randomItem].equals("Parşömen")) {

            rbA.setText( "Parşömen");
            rbB.setText( "Parşümen");


        } else if (bircok[randomItem].equals("Makine")) {

            rbA.setText("Makine");
            rbB.setText( "Makina");


        } else if (bircok[randomItem].equals("Egzoz")) {
            rbA.setText("Egsoz");
            rbB.setText("Egzoz");


        } else if (bircok[randomItem].equals("Atölye")) {

            rbA.setText( "Atelye");
            rbB.setText( "Atölye");


        } else if (bircok[randomItem].equals("Hafriyat")) {

            rbA.setText( "Hafriyat");
            rbB.setText( "Harfiyat");


        } else if (bircok[randomItem].equals("Şofben")) {

            rbA.setText( "Şofben");
            rbB.setText( "Şohben");


        } else if (bircok[randomItem].equals("Ateş böceği")) {

            rbA.setText( "Ateşböceği");
            rbB.setText( "Ateş böceği");


        } else if (bircok[randomItem].equals("Yüzükoyun")) {

            rbA.setText( "Yüzü Koyun");
            rbB.setText( "Yüzükoyun");


        } else if (bircok[randomItem].equals("Köpek Balığı")) {

            rbA.setText( "Köpek Balığı");
            rbB.setText( "Köpekbalığı");


        } else if (bircok[randomItem].equals("Hafta sonu")) {

            rbA.setText("Hafta sonu");
            rbB.setText("Haftasonu");


        } else if (bircok[randomItem].equals("Kuru yemiş")) {

            rbA.setText("Kuruyemiş");
            rbB.setText("Kuru yemiş");


        } else if (bircok[randomItem].equals("Oysaki")) {

            rbA.setText("Oysa ki");
            rbB.setText("Oysaki");


        } else if (bircok[randomItem].equals("Her gün")) {

            rbA.setText("Her gün");
            rbB.setText("Hergün");


        } else if (bircok[randomItem].equals("Herkes")) {

            rbA.setText( "Herkes");
            rbB.setText("Herkez");


        } else if (bircok[randomItem].equals("Art arda")) {

            rbA.setText( "Art arda");
            rbB.setText("Artarda");


        } else if (bircok[randomItem].equals("Tıraş")) {

            rbA.setText( "Tıraş");
            rbB.setText("Traş");


        } else if (bircok[randomItem].equals("Herhangi")) {

            rbA.setText("Herhangi");
            rbB.setText( "Her hangi");


        } else if (bircok[randomItem].equals("Kibrit")) {

            rbA.setText("Kirbit");
            rbB.setText("Kibrit");


        } else if (bircok[randomItem].equals("Affedersiniz")) {

            rbA.setText( "Af edersiniz");
            rbB.setText( "Affedersiniz");


        } else if (bircok[randomItem].equals("Hiçbir şey")) {

            rbA.setText( "Hiçbir şey");
            rbB.setText("Hiçbirşey");


        } else if (bircok[randomItem].equals("Dershane")) {

            rbA.setText("Dershane");
            rbB.setText("Dersane");


        } else if (bircok[randomItem].equals("Ateşkes")) {

            rbA.setText("Ateş kes");
            rbB.setText("Ateşkes");


        } else if (bircok[randomItem].equals("Laboratuvar")) {

            rbA.setText( "Laboratuar");
            rbB.setText( "Laboratuvar");


        } else if (bircok[randomItem].equals("Travma")) {

            rbA.setText( "Travma");
            rbB.setText("Tıravma");


        } else if (bircok[randomItem].equals("Babaanne")) {

            rbA.setText( "Baba anne");
            rbB.setText("Babaanne");


        } else if (bircok[randomItem].equals("Uluslararası")) {

            rbA.setText("Uluslararası");
            rbB.setText( "Uluslar arası");


        } else if (bircok[randomItem].equals("Alaca karanlık")) {

            rbA.setText("Alaca karanlık");
            rbB.setText("Alacakaranlık");


        } else if (bircok[randomItem].equals("Kilitledi")) {

            rbA.setText("Kitledi");
            rbB.setText("Kilitledi");


        } else if (bircok[randomItem].equals("Sürpriz")) {

            rbA.setText( "Süpriz");
            rbB.setText("Sürpriz");


        } else if (bircok[randomItem].equals("Kravat")) {

            rbA.setText("Kravat");
            rbB.setText( "Kıravat");


        } else if (bircok[randomItem].equals("Fark etmek")) {

            rbA.setText("Farketmek");
            rbB.setText("Fark etmek");


        } else if (bircok[randomItem].equals("Şarj")) {

            rbA.setText("Şarj");
            rbB.setText("Şarz");


        } else if (bircok[randomItem].equals("Aslan")) {

            rbA.setText("Aslan");
            rbB.setText("Arslan");

        } else if (bircok[randomItem].equals("Her şey")) {

            rbA.setText("Herşey");
            rbB.setText("Her şey");


        } else if (bircok[randomItem].equals("Birtakım")) {

            rbA.setText("Bir takım");
            rbB.setText("Birtakım");


        } else if (bircok[randomItem].equals("Çeyiz")) {

            rbA.setText("Çeyiz");
            rbB.setText("Ceyiz");


        } else if (bircok[randomItem].equals("Halbuki")) {

            rbA.setText("Halbuki");
            rbB.setText("Halbu ki");

        } else if (bircok[randomItem].equals("Zarafet")) {

            rbA.setText("Zerafet");
            rbB.setText("Zarafet");

        } else if (bircok[randomItem].equals("Dinozor")) {

            rbA.setText("Dinazor");
            rbB.setText("Dinozor");

        } else if (bircok[randomItem].equals("Şefkat")) {

            rbA.setText("Şefkat");
            rbB.setText("Şevkat");

        } else if (bircok[randomItem].equals("Unvan")) {

            rbA.setText("Ünvan");
            rbB.setText("Unvan");

        } else if (bircok[randomItem].equals("Gözyaşı")) {

            rbA.setText("Göz yaşı");
            rbB.setText("Gözyaşı");

        } else if (bircok[randomItem].equals("gidişgeliş")) {

            rbA.setText("gidişgeliş");
            rbB.setText("gidiş geliş");

        } else if (bircok[randomItem].equals("çokbilmiş")) {

            rbA.setText("çok bilmiş");
            rbB.setText("çokbilmiş");

        } else if (bircok[randomItem].equals("fark etmek")) {

            rbA.setText("farketmek");
            rbB.setText("fark etmek");

        } else if (bircok[randomItem].equals("bu günlerde")) {

            rbA.setText("bu günlerde");
            rbB.setText("bugünlerde");

        } else if (bircok[randomItem].equals("bir şeyler")) {

            rbA.setText("birşeyler");
            rbB.setText("bir şeyler");


        } else if (bircok[randomItem].equals("bir şeye")) {

            rbA.setText("bir şeye");
            rbB.setText("birşeye");


        } else if (bircok[randomItem].equals("ben de kullanıyorum")) {

            rbA.setText("bende kullanıyorum");
            rbB.setText("ben de kullanıyorum");

        } else if (bircok[randomItem].equals("baş etmek")) {

            rbA.setText("başetmek");
            rbB.setText("baş etmek");

        } else if (bircok[randomItem].equals("az çok")) {

            rbA.setText("az çok");
            rbB.setText("azçok");


        } else if (bircok[randomItem].equals("ayrım")) {

            rbA.setText("ayrım");
            rbB.setText("ayırım");


        } else if (bircok[randomItem].equals("artırmak")) {

            rbA.setText("artırmak");
            rbB.setText("arttırmak");


        } else if (bircok[randomItem].equals("arkadaştan")) {

            rbA.setText("arkadaştan");
            rbB.setText("arkadaşdan");

        } else if (bircok[randomItem].equals("Arapçası")) {

            rbA.setText("Arapça'sı");
            rbB.setText("Arapçası");

        } else if (bircok[randomItem].equals("Allah aşkına")) {

            rbA.setText("Allahaşkına");
            rbB.setText("Allah aşkına");

        } else if (bircok[randomItem].equals("hâlâ")) {

            rbA.setText("hâlâ");
            rbB.setText("hala");

        } else if (bircok[randomItem].equals("hammadde")) {

            rbA.setText("ham madde");
            rbB.setText("hammadde");

        } else if (bircok[randomItem].equals("her gün")) {

            rbA.setText("her gün");
            rbB.setText("hergün");

        } else if (bircok[randomItem].equals("hukukun")) {

            rbA.setText("hukukun");
            rbB.setText("hukuğun");

        } else if (bircok[randomItem].equals("İngilizcenin")) {

            rbA.setText("İngilizce'nin");
            rbB.setText("İngilizcenin");

        } else if (bircok[randomItem].equals("işyeri")) {

            rbA.setText("işyeri");
            rbB.setText("iş yeri");

        } else if (bircok[randomItem].equals("Lise 1'de bize öğretilen")) {

            rbA.setText("Lise 1 de bize öğretilen");
            rbB.setText("Lise 1'de bize öğretilen");

        } else if (bircok[randomItem].equals("maşallah")) {

            rbA.setText("maaşallah");
            rbB.setText("maşallah");

        } else if (bircok[randomItem].equals("mademki")) {

            rbA.setText("mademki");
            rbB.setText("madem ki");

        } else if (bircok[randomItem].equals("maalesef")) {

            rbA.setText("maalesef");
            rbB.setText("malesef");

        } else if (bircok[randomItem].equals("olup da")) {

            rbA.setText("olup ta");
            rbB.setText("olup da");

        } else if (bircok[randomItem].equals("övünç")) {

            rbA.setText("öğünç");
            rbB.setText("övünç");

        } else if (bircok[randomItem].equals("rastgele")) {

            rbA.setText("rastgele");
            rbB.setText("rasgele");

        } else if (bircok[randomItem].equals("tabii")) {

            rbA.setText("tabii");
            rbB.setText("tabi");

        } else if (bircok[randomItem].equals("tetanos")) {

            rbA.setText("tetenoz");
            rbB.setText("tetanos");

        } else if (bircok[randomItem].equals("ya da")) {

            rbA.setText("ya da");
            rbB.setText("yada");

        } else if (bircok[randomItem].equals("zıddı")) {

            rbA.setText("zıtı");
            rbB.setText("zıddı");

        }
    }
}
