package dk.itu.local_garbage.database;

public class ItemsDbSchema {
    public static final class ItemTable {
        public static final String NAME = "Items";

        public static final class Cols {
            public static final String WHAT = "what";
            public static final String PLACE = "placeC";  //where is a keyword in SQL
        }
    }
}
