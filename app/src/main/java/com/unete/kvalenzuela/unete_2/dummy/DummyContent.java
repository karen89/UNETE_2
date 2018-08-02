package com.unete.kvalenzuela.unete_2.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample nombreAC for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    /*private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }*/
    static {
        addItem(new DummyItem("1", "UNE A.C.", "Unión y Esperanza Yucateca A.C.",
                "Pablo Rosete", "uneyayuda@gmail.com","9991547645","ricosuave1",
                "Fundacion que ayuda a niños Mérida,Yucatán, México. \nMejorar las condiciones de vida en las comunidades rurales de los niños menores de 15 años en el sur del Estado de Yucatán, México. Esto, mediante programas diseñados para mejorar el carácter, autoestima y posibilidades económicas del niño.",
                "374859"));
        addItem(new DummyItem("2", "UNE ", "Unión y Esperanza Yucateca A.C.",
                "Pablo Rosete", "uneyayuda@gmail.com","9991547645","ricosuave1",
                "Fundacion que ayuda a niños Mérida,Yucatán, México. \nMejorar las condiciones de vida en las comunidades rurales de los niños menores de 15 años en el sur del Estado de Yucatán, México. Esto, mediante programas diseñados para mejorar el carácter, autoestima y posibilidades económicas del niño.",
                "23465"));
        addItem(new DummyItem("3", "UNE A.C.", "Unión y Esperanza Yucateca A.C.",
                "Pablo Rosete", "uneyayuda@gmail.com","9991547645","ricosuave1",
                "Fundacion que ayuda a niños Mérida,Yucatán, México. \nMejorar las condiciones de vida en las comunidades rurales de los niños menores de 15 años en el sur del Estado de Yucatán, México. Esto, mediante programas diseñados para mejorar el carácter, autoestima y posibilidades económicas del niño.",
                "35523"));
        addItem(new DummyItem("4", "UNE", "Unión y Esperanza Yucateca A.C.",
                "Pablo Rosete", "uneyayuda@gmail.com","9991547645","ricosuave1",
                "Fundacion que ayuda a niños Mérida,Yucatán, México. \nMejorar las condiciones de vida en las comunidades rurales de los niños menores de 15 años en el sur del Estado de Yucatán, México. Esto, mediante programas diseñados para mejorar el carácter, autoestima y posibilidades económicas del niño.",
                "65347"));
        addItem(new DummyItem("5", "UNE A.C.", "Unión y Esperanza Yucateca A.C.",
                "Pablo Rosete", "uneyayuda@gmail.com","9991547645","ricosuave1",
                "Fundacion que ayuda a niños Mérida,Yucatán, México. \nMejorar las condiciones de vida en las comunidades rurales de los niños menores de 15 años en el sur del Estado de Yucatán, México. Esto, mediante programas diseñados para mejorar el carácter, autoestima y posibilidades económicas del niño.",
                "34678"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /*private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore responsable information here.");
        }
        return builder.toString();
    }*/

    /**
     * A dummy item representing a piece of nombreAC.
     */
    public static class DummyItem {
        public final String id;
        public final String nombreCorto;
        public final String nombreAC;
        public final String responsable;
        public final String email;
        public final String celular;
        public final String contrasena;
        public final String descripcion;
        public final String cuentaBancaria;

        public DummyItem(String id, String nombreCorto, String nombreAC, String responsable,
                         String email, String celular, String contrasena,
                         String descripcion, String cuentaBancaria) {
            this.id = id;
            this.nombreCorto = nombreCorto;
            this.nombreAC = nombreAC;
            this.responsable = responsable;
            this.email = email;
            this.celular = celular;
            this.contrasena = contrasena;
            this.descripcion = descripcion;
            this.cuentaBancaria = cuentaBancaria;
        }

        @Override
        public String toString() {
            return nombreAC;
        }
    }
}
