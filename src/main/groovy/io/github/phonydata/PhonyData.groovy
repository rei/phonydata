package io.github.phonydata;

import io.github.phonydata.reader.DatabaseDataSetReader
import io.github.phonydata.reader.GroovyDataSetReader
import io.github.phonydata.writer.DatabaseDataSetWriter
import io.github.phonydata.writer.GroovyDataSetWriter

import javax.sql.DataSource

public abstract class PhonyData {

    public static void readInto(String groovyDataset, DataSource dest) {
        readInto(new ByteArrayInputStream(groovyDataset.bytes))
    }
    
    public static void readInto(InputStream input, DataSource dest) {
        def reader = new GroovyDataSetReader(input)
        def writer = new DatabaseDataSetWriter(dest, false)
        writer.write(reader.read())
    }
    
    public static void readInto(DataSource src, Collection<String> tables, OutputStream dest) {
        def reader = new DatabaseDataSetReader(src, tables)
        def writer = new GroovyDataSetWriter(dest)
        writer.write(reader.read())
    }
}