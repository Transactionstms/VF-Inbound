package com.onest.security;

import com.onest.net.Connection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TestingSec {

    public static void main(String[] args) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            Authenticate auth = new Authenticate();
            String phrase = "admconsola141009";
            System.out.println(auth.encrypt("admconsola141009"));
            System.out.println("===========================>");
            System.out.println(auth.decrypt("ugdOYqweX1t/QS8xrbmatg=="));
            System.out.println("===========================>");
            /*if (!auth.verifyLogin("admin", "admconsola141009")) {
                System.out.println("no es correcto");
            } else {
                System.out.println("es correcto");
                Connection[] conn = auth.getServers();
                System.out.println(conn.toString());
            }*/

            System.out.println("itrafico    " + auth.encrypt("issstetrafico120908"));
            System.out.println("ioperacion  " + auth.encrypt("isssteoperacion120908"));
            System.out.println("issste      " + auth.encrypt("isssteissste120908"));
            System.out.println("issste      " + auth.encrypt("isssteissste120908"));
            System.out.println("issste      " + auth.encrypt("isssteissste120908"));
            System.out.println("rmartinez   " + auth.encrypt("issstemartinez281008"));
            System.out.println("gloya       " + auth.encrypt("isssteloya281008"));
            System.out.println("conagra     " + auth.encrypt("issste#conagrafoods240908"));
            System.out.println("relampago   " + auth.encrypt("issste#prodadrelampago240908"));
            System.out.println("emetropolitana " + auth.encrypt("issste#metropolitanaemb240908"));
            System.out.println("cpalmolive  " + auth.encrypt("issste#colgate240908"));
            System.out.println("bonafont    " + auth.encrypt("issste#bonafont240908"));
            System.out.println("sabormex    " + auth.encrypt("issste#sabormex240908"));
            System.out.println("mabe        " + auth.encrypt("issste#mabe240908"));
            System.out.println("unilever    " + auth.encrypt("issste#unilever240908"));
            System.out.println("procter     " + auth.encrypt("issste#gambleprocter240908"));
            System.out.println("gamesa      " + auth.encrypt("issste#grupogamesa240908"));
            System.out.println("arrozsos    " + auth.encrypt("issste#arrozsosmexico240908"));
            System.out.println("gptissue    " + auth.encrypt("issste#tissuegeorgia240908"));
            System.out.println("ppmadera    " + auth.encrypt("issste#practicosmadera240908"));
            System.out.println("mtropico    " + auth.encrypt("issste#multiempaquestropico240908"));
            System.out.println("henckelcapital " + auth.encrypt("issste#capitalhenkel240908"));
            System.out.println("kelloggs    " + auth.encrypt("issste#companykellogs240908"));
            System.out.println("isssterf    " + auth.encrypt("isssterfusr051108"));
            System.out.println("iusarf      " + auth.encrypt("iusacellrfusr051108"));
            System.out.println("iusaoper    " + auth.encrypt("iusacelloperusr051108"));
            System.out.println("nestlerf    " + auth.encrypt("nestlerf271108"));
            System.out.println("onsysrf     " + auth.encrypt("onsysrf111208"));
            System.out.println("onsyspc     " + auth.encrypt("onsyspc111208"));
            System.out.println("carlos      " + auth.encrypt("gaby"));
            System.out.println("omar        " + auth.encrypt("pumases"));
            System.out.println("mespinoza   " + auth.encrypt("3321"));
            System.out.println("jchavez     " + auth.encrypt("josefo"));
            System.out.println("dmurgado    " + auth.encrypt("2332"));
            System.out.println("cramos      " + auth.encrypt("1208"));
            System.out.println("jmartinez   " + auth.encrypt("serkom"));
            System.out.println("juribe      " + auth.encrypt("pierrot"));
            System.out.println("jgomez      " + auth.encrypt("1808"));
            System.out.println("lpelcastre  " + auth.encrypt("1887"));
            System.out.println("iloyola     " + auth.encrypt("peluche"));
            System.out.println("cperez      " + auth.encrypt("cperez1"));
            System.out.println("dalvarez    " + auth.encrypt("dalvarez1"));
            System.out.println("jrubi       " + auth.encrypt("jrubi1"));
            System.out.println("operez      " + auth.encrypt("operez1"));
            System.out.println("cgarcia     " + auth.encrypt("??o8"));
            System.out.println("dsanchez    " + auth.encrypt("dsanchez1"));
            System.out.println();
            System.out.println("jaguilar    " + auth.encrypt("jaguilar1"));
            System.out.println("gherrera    " + auth.encrypt("gherrera1"));
            System.out.println("mgonzalez   " + auth.encrypt("mgonzalez1"));
            System.out.println("lvargas     " + auth.encrypt("lvargas1"));
            System.out.println("jramos      " + auth.encrypt("jramos1"));
            System.out.println("mortega     " + auth.encrypt("mortega1"));
            System.out.println("cihernandez " + auth.encrypt("cihernandez1"));
            System.out.println("ealvarado   " + auth.encrypt("ealvarado1"));
            System.out.println("jrangel     " + auth.encrypt("jrangel1"));
            System.out.println("jmretana    " + auth.encrypt("jmretana1"));
            System.out.println("iromero     " + auth.encrypt("iromero1"));
            System.out.println("kvianey     " + auth.encrypt("kvianey1"));
            System.out.println("murbano     " + auth.encrypt("murbano1"));
            System.out.println("pbarrera    " + auth.encrypt("pbarrera1"));
            System.out.println("ggallegos\t" + auth.encrypt("ggallegos1"));
            System.out.println("jguadalupe\t" + auth.encrypt("jguadalupe1"));
            System.out.println("comodin1    " + auth.encrypt("comodin11"));
            System.out.println("comodin2    " + auth.encrypt("comodin21"));
            System.out.println("comodin3    " + auth.encrypt("comodin31"));
            System.out.println("comodin4    " + auth.encrypt("comodin41"));
            System.out.println("comodin5    " + auth.encrypt("comodin51"));
            System.out.println("comodin6    " + auth.encrypt("comodin61"));
            System.out.println("comodin7    " + auth.encrypt("comodin71"));
            System.out.println("comodin8    " + auth.encrypt("comodin81"));
            System.out.println("comodin9    " + auth.encrypt("comodin91"));
            System.out.println("comodin10   " + auth.encrypt("comodin101"));
            System.out.println("mresendiz   " + auth.encrypt("mresendiz"));
            System.out.println("admin       " + auth.encrypt("admin"));
            System.out.println("admin       " + auth.encrypt("admincompras"));
            System.out.println("vstoreusra  " + auth.encrypt("vstoreusra"));
            System.out.println("vstoreusrb  " + auth.encrypt("vstoreusrb"));
            System.out.println("vstoreusrc  " + auth.encrypt("vstoreusrc"));
            System.out.println("vstoreusrd  " + auth.encrypt("vstoreusrd"));
            System.out.println("vstoreusre  " + auth.encrypt("vstoreusre"));
            System.out.println("vstoreusrf  " + auth.encrypt("vstoreusrf"));

            System.out.println("vstoreadm  " + auth.encrypt("vstoreadm"));
            System.out.println("vstoredir  " + auth.encrypt("vstoredir"));
            System.out.println("vstoregtea " + auth.encrypt("vstoregtea"));
            System.out.println("vstoregteb " + auth.encrypt("vstoregteb"));
            System.out.println("onsyscdpc  " + auth.encrypt("onsyscdpc"));
            System.out.println("onsyscdrf  " + auth.encrypt("onsyscdrf"));
            System.out.println(auth.decrypt("vCrTMXA+CGWpKd0QVPf/OQ=="));

            System.out.println("operaciones1 " + auth.encrypt("infante060309"));
            System.out.println("operaciones2 " + auth.encrypt("eleazar060309"));
            System.out.println("operaciones3 " + auth.encrypt("canales060309"));

            System.out.println("CESAR " + auth.encrypt("cesar1123581321"));

            byte[] in = phrase.getBytes();
            byte[] out = digest.digest(in);
            String tmpStr = new String(BASE64.encode(out));
            System.out.println(tmpStr + " ");
            String str2 = new String(BASE64.decode(tmpStr));
            System.out.println(auth.decrypt(tmpStr));
        } catch (NoSuchAlgorithmException e) {
        }
    }
}

/* Location:           C:\Users\OS7-PRO\Desktop\onest-common-utils-0.0.1.jar
 * Qualified Name:     com.onest.security.TestingSec
 * JD-Core Version:    0.6.0
 */