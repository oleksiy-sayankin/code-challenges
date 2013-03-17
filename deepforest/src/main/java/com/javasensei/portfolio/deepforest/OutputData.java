package com.javasensei.portfolio.deepforest;

import com.javasensei.portfolio.math.IPoint;
import com.javasensei.portfolio.math.Point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * @author oleksiy sayankin
 */
public class OutputData {
    private boolean forestIsDeep;
    private IPoint exit;

    public void read(Reader reader) throws IOException {
        BufferedReader br = new BufferedReader(reader);
        try {
            String firstLine = br.readLine();
            if (Constants.YES.equals(firstLine.trim().toUpperCase())) {
                setForestIsDeep(true);
            } else {
                setForestIsDeep(false);
                String secondLine = br.readLine();
                String[] values = secondLine.split(" ");
                double x = Double.valueOf(values[0]);
                double y = Double.valueOf(values[1]);
                setExit(new Point(x, y));
            }
        } finally {
            br.close();
        }
    }


    public void write(Writer writer) throws IOException {
        try {
            if (forestIsDeep) {
                writer.write(Constants.YES);
            } else {
                writer.write(Constants.NO);
                writer.write("\n");
                writer.write(exit.getX() + " " + exit.getY());
            }
            writer.flush();
        } finally {
            writer.close();
        }
    }

    public void setForestIsDeep(boolean forestIsDeep) {
        this.forestIsDeep = forestIsDeep;
    }

    public void setExit(IPoint exit) {
        this.exit = exit;
    }

    public IPoint getExit() {
        return exit;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof OutputData)) {
            return false;
        }
        if (this == other) {
            return true;
        }
        OutputData otherOutputData = (OutputData) other;
        return this.forestIsDeep == otherOutputData.forestIsDeep;
    }

    @Override
    public int hashCode() {
        return (forestIsDeep ? 31 : 32) * exit.hashCode();
    }

    @Override
    public String toString() {
        return (forestIsDeep ? Constants.YES : Constants.NO) + ", " + exit;
    }
}
