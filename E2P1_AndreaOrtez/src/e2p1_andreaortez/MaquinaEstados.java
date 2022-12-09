package e2p1_andreaortez;

import java.util.ArrayList;

public class MaquinaEstados {

    private ArrayList<String> estados;
    private ArrayList<String> estados_aceptacion = new ArrayList();
    private ArrayList<String> aristas;
    private String estado_actual;

    public MaquinaEstados(String estados, String aristas) {
        this.estados = splitStr(estados, ';');
        extractAcceptNodes();
        this.aristas = splitStr(aristas, ';');
        this.estado_actual = this.estados.get(0);
        
    }

    //Getters and Setters
    public ArrayList<String> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<String> estados) {
        this.estados = estados;
    }

    public ArrayList<String> getEstados_aceptacion() {
        return estados_aceptacion;
    }

    public void setEstados_aceptacion(ArrayList<String> estados_aceptacion) {
        this.estados_aceptacion = estados_aceptacion;
    }

    public ArrayList<String> getAristas() {
        return aristas;
    }

    public void setAristas(ArrayList<String> aristas) {
        this.aristas = aristas;
    }

    public String getEstado_actual() {
        return estado_actual;
    }

    public void setEstado_actual(String estado_actual) {
        this.estado_actual = estado_actual;
    }
    //Getters and Setters

    public ArrayList<String> splitStr(String str, char delim) {
        ArrayList<String> temp = new ArrayList<String>();
        String[] token;

        token = str.split(String.valueOf(delim));

        for (int i = 0; i < token.length; i++) {
            temp.add(token[i]);
        }
        return temp;
    }

    public void extractAcceptNodes() {
        for (int i = 0; i < estados.size(); i++) {
            String a = estados.get(i);

            if (a.contains(".")) {
                a = a.substring(1);
                estados.set(i, a);
                estados_aceptacion.add(a);
            }
        }
    }

    public String computeStr(String str) {
        estado_actual = estados.get(0);
        String resp = "";

        for (int i = 0; i < str.length(); i++) {
            char a = str.charAt(i);
            String temparr = GetArista(estado_actual + ',' + a);
            
            
            if (!temparr.equals("")) {
                resp += (estado_actual + ':' + a);
                String [] arr = temparr.split(",");
                estado_actual = arr[2];
                resp += (estado_actual + '\n');
            } else {
                return "Rechazada";
            }
        }
        if (estados_aceptacion.contains(estado_actual)) {
                resp+="\nAceptada";
            } else {
                resp+="\nRechazada";
            }
        return resp;
    }

    public String GetArista(String cad) {
        for (int i = 0; i < aristas.size(); i++) {
            if (aristas.get(i).contains(cad)) {
                return aristas.get(i);
            }
        }
        return "";
    }
}//clase
