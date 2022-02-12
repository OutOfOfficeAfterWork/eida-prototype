package org.outofoffice.eida.shard;

public class ShardServerFileFacade {

    //private static final String FILE_ROOT = "/";
    private static final String FILE_ROOT = "file-root/";
    private static final String SHARD_PATH = FILE_ROOT + "eida-shard/";
    private static final String TABLE_PATH = SHARD_PATH + "table/";
    private static final String META_PATH = SHARD_PATH + "meta/";

    // public static Map<String, String> readTableFile(String tableName) {
    //     String filePath = TABLE_PATH + tableName;
    //     try {
    //         return readFileAsMap(filePath);
    //     } catch (FileNotFoundException e) {
    //         String targetFilePath = e.getMessage();
    //         throw new TableNotFoundException(targetFilePath);
    //     }
    // }

    // public static Map<String, String> readShardFile() {
    //     String filePath = META_PATH + "shard";
    //     try {
    //         return readFileAsMap(filePath);
    //     } catch (FileNotFoundException e) {
    //         String targetFilePath = e.getMessage();
    //         throw new IllegalStateException("shard file not found in " + targetFilePath);
    //     }
    // }

}
