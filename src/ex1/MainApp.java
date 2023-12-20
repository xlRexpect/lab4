package ex1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class MainApp {
    public static void main(String[] args) throws IOException {
        Scanner debugscan=new Scanner(System.in);
        BufferedReader flux_in= new BufferedReader(new FileReader("electronice.txt"));
        String linie;
        int n=0;
        while((linie=flux_in.readLine())!=null){
            n++;
        }
        flux_in.close();
        List<Echipament> echipamentList = new ArrayList<Echipament>();

        String den;
        int nri;
        float pre;
        String zon;
        Status sta;
        Tip tip;
        int ppi,pca;
        String dpm;
        ModTip mti;
        int pto;
        Format frm;
        String tmo;
        float vpr;
        int chd;
        SistemOp sop;

        BufferedReader flux_in1= new BufferedReader(new FileReader("electronice.txt"));
        for(int i=0;i<n;i++){
            linie=flux_in1.readLine();
            StringTokenizer st= new StringTokenizer(linie,";");
            den=st.nextToken().toString();
            nri=Integer.parseInt(st.nextToken().toString());
            pre=Float.parseFloat(st.nextToken().toString());
            zon=st.nextToken().toString();
            sta=Status.valueOf(st.nextToken().toString().toUpperCase());
            String tipString = st.nextToken().toString();
            if(tipString.equals("sistem de calcul")){
                tip=Tip.valueOf("SISTEMDECALCUL");
            }else {
                //System.out.println(st.nextToken().toString());
                //String debug=debugscan.next();
                tip = Tip.valueOf(tipString.toUpperCase());
            }
            switch (tip){

                case IMPRIMANTA -> {
                    ppi=Integer.parseInt(st.nextToken().toString());
                    dpm=st.nextToken().toString();
                    pca=Integer.parseInt(st.nextToken().toString());
                    //System.out.println(st.nextToken().toString());
                    //String debug=debugscan.next();
                    mti=ModTip.valueOf(st.nextToken().toString().toUpperCase());
                    Imprimante imp=new Imprimante(den,nri,pre,zon,sta,tip,ppi,dpm,pca,mti);
                    echipamentList.add(imp);
                }
                case COPIATOR -> {
                    pto=Integer.parseInt(st.nextToken().toString());
                    frm=Format.valueOf(st.nextToken().toString().toUpperCase());
                    Copiatoare cop=new Copiatoare(den,nri,pre,zon,sta,tip,pto,frm);
                    echipamentList.add(cop);
                }
                case SISTEMDECALCUL -> {
                    tmo=st.nextToken().toString();
                    vpr=Float.parseFloat(st.nextToken().toString());
                    chd=Integer.parseInt(st.nextToken().toString());
                    sop=SistemOp.valueOf(st.nextToken().toString().toUpperCase());
                    SistemeDeCalcul sca=new SistemeDeCalcul(den,nri,pre,zon,sta,tip,tmo,vpr,chd,sop);
                    echipamentList.add(sca);
                }
            }
        }
        flux_in1.close();
        int UI=0;
        Scanner scan=new Scanner(System.in);
        do{
            System.out.println("0=exit");
            System.out.println("1=afisare echipamente");
            System.out.println("2=afisare imprimante");
            System.out.println("3=afisare copiatoare");
            System.out.println("4=afisare sisteme de calcul");
            System.out.println("5=Modificarea stării în care se află un echipament ");
            System.out.println("6=Setarea unui anumit mod de scriere pentru o imprimantă");
            System.out.println("7=Setarea unui format de copiere pentru copiatoare");
            System.out.println("8=Instalarea unui anumit sistem de operare pe un sistem de calcul ");
            System.out.println("9=Afişarea echipamentelor vândute ");
            System.out.println("10=serializarea / deserializare ");
            boolean b = false;
            while(b==false){
                String UIs = null;
                try {
                    UIs=scan.next();
                    UI = Integer.parseInt(UIs);
                    b=true;
                    // Use the integer value
                } catch (NumberFormatException e) {
                    System.err.println("The string is not a valid integer: "+UIs);
                    // Handle the exception or provide an alternative behavior
                }
            }
            switch (UI){
                case 0->{
                    System.out.println("closing program");
                    //break;
                }
                case 1->{
                    for(int i=0;i<n;i++){
                        System.out.println(echipamentList.get(i));
                    }
                    // break;
                }
                case 2->{
                    for(int i=0;i<n;i++){
                        if((echipamentList.get(i).getTip())==Tip.valueOf("IMPRIMANTA")){
                            System.out.println(echipamentList.get(i));
                        }
                    }
                }
                case 3->{
                    for(int i=0;i<n;i++){
                        if((echipamentList.get(i).getTip())==Tip.valueOf("COPIATOR")){
                            System.out.println(echipamentList.get(i));
                        }
                    }
                }
                case 4->{
                    for(int i=0;i<n;i++){
                        if((echipamentList.get(i).getTip())==Tip.valueOf("SISTEMDECALCUL")){
                            System.out.println(echipamentList.get(i));
                        }
                    }
                }
                case 5->{
                    Scanner inpscanner = new Scanner(System.in);
                    System.out.println("Denumirea echipamentului?");
                    String UIs=inpscanner.nextLine();
                    for(int i=0;i<n;i++){
                        if(echipamentList.get(i).getDenumire().equals(UIs)){
                            System.out.println("status of product: "+echipamentList.get(i).getStatusEch());
                            System.out.println("what is the new status(ACHIZITIONAT,EXPUS,VANDUT), choose between 1-3");
                            int UI2=scan.nextInt();
                            switch(UI2){
                                case 1->{
                                    echipamentList.get(i).setStatusEch(Status.valueOf("ACHIZITIONAT"));
                                }
                                case 2->{
                                    echipamentList.get(i).setStatusEch(Status.valueOf("EXPUS"));
                                }
                                case 3->{
                                    echipamentList.get(i).setStatusEch(Status.valueOf("VANDUT"));
                                }
                            }
                        }
                    }
                }
                case 6->{
                    Scanner inpscanner = new Scanner(System.in);
                    System.out.println("Denumirea echipamentului?");
                    String UIs=inpscanner.nextLine();
                    for(int i=0;i<n;i++){
                        if(echipamentList.get(i).getTip().equals(Tip.valueOf("IMPRIMANTA")))
                            if(echipamentList.get(i).getDenumire().equals(UIs)){
                                Imprimante aux=(Imprimante)echipamentList.get(i);
                                System.out.println("modul de tiparire: "+aux.getModTip());
                                System.out.println("modul de tiparire noua(COLOR,NEGRU), choose between 1-2");
                                int UI2=scan.nextInt();
                                switch (UI2){
                                    case 1->{
                                        aux.setModTip(ModTip.valueOf("COLOR"));
                                    }
                                    case 2->{
                                        aux.setModTip(ModTip.valueOf("NEGRU"));
                                    }
                                }
                                echipamentList.set(i,aux);
                            }
                    }
                }
                case 7->{
                    Scanner inpscanner = new Scanner(System.in);
                    System.out.println("Denumirea echipamentului?");
                    String UIs=inpscanner.nextLine();
                    for(int i=0;i<n;i++){
                        if(echipamentList.get(i).getTip().equals(Tip.valueOf("COPIATOR")))
                            if(echipamentList.get(i).getDenumire().equals(UIs)){
                                Copiatoare aux=(Copiatoare)echipamentList.get(i);
                                System.out.println("formatul curent: "+aux.getFormat());
                                System.out.println("formatul nou(A3,A4)");
                                UIs=scan.next();
                                aux.setFormat(Format.valueOf(UIs));
                                echipamentList.set(i,aux);
                            }
                    }
                }
                case 8->{
                    Scanner inpscanner = new Scanner(System.in);
                    System.out.println("Denumirea echipamentului?");
                    String UIs=inpscanner.nextLine();
                    for(int i=0;i<n;i++){
                        if(echipamentList.get(i).getTip().equals(Tip.valueOf("SISTEMDECALCUL")))
                            if(echipamentList.get(i).getDenumire().equals(UIs)){
                                SistemeDeCalcul aux=(SistemeDeCalcul)echipamentList.get(i);
                                System.out.println("sistemul de operare curenta: "+aux.getSistemOp());
                                System.out.println("sistemul de operare nou(Windows,Linux), choose between 1-2");
                                int UI2=scan.nextInt();
                                switch (UI2){
                                    case 1->{
                                        aux.setSistemOp(SistemOp.valueOf("WINDOWS"));
                                    }
                                    case 2->{
                                        aux.setSistemOp(SistemOp.valueOf("LINUX"));
                                    }
                                }
                                echipamentList.set(i,aux);
                            }
                    }
                }
                case 9->{
                    for(int i=0;i<n;i++){
                        if(echipamentList.get(i).getStatusEch()==Status.valueOf("VANDUT")){
                            System.out.println(echipamentList.get(i));
                        }
                    }
                }
                case 10->{
                    System.out.println("serializare / deserializare (1-2)");
                    int UI2= scan.nextInt();
                    switch(UI2){
                        case 1->{
                            scrie(echipamentList,"echip.bin");

                        }
                        case 2->{
                            echipamentList.clear();
                            echipamentList =  (List<Echipament>) citeste("echip.bin");
                        }
                    }
                }
                case 11->{
                    List<Echipament> imprimanteList = echipamentList.stream()
                            .filter((a)->a instanceof Imprimante)
                            .collect(Collectors.toList());
                    imprimanteList.forEach(System.out::println);
                }
                default -> {
                    System.out.println("error");
                }
            }
        }while(UI!=0);


    }

    static void scrie(Object o, String fis) {
        try {
            FileOutputStream f = new FileOutputStream(fis);
            ObjectOutputStream oos = new ObjectOutputStream(f);
            oos.writeObject(o);
            oos.close();
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Object citeste(String fis) {
        try {
            FileInputStream f = new FileInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(f);
            Object o=ois.readObject();
            ois.close();
            f.close();
            return o;
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

