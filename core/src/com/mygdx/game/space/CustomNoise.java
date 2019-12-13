package com.mygdx.game.space;

import java.io.IOException;
import java.util.Random;

public class CustomNoise {
    private float[][] map;

    private static com.mygdx.game.space.OpenSimplexNoise INTL_NOISE = new com.mygdx.game.space.OpenSimplexNoise();
    private static Random INTL_RANDOM = new Random();

    public CustomNoise(int x, int y) {
        map = new float[y][x];

        for (int i = 0; i < 1000; i++) {
            raise(map);
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] += (float)INTL_NOISE.eval(j/10f, i/10f);
            }
        }

        float m1 = min(map);

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] -= m1;
            }
        }

        float m2 = max(map);

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = map[i][j] / m2 * 10f;
            }
        }
    }

    private void raise(float[][] initHeightMap) {
        int w = initHeightMap[0].length;
        int h = initHeightMap.length;

        int y = INTL_RANDOM.nextInt(h-1) + 1;
        int x = INTL_RANDOM.nextInt(w-1) + 1;
        int rad = (w + h) / 20;
        int sl = INTL_RANDOM.nextInt(180);

        float[] vertx = new float[4];
        float[] verty = new float[4];

        vertx[0] = x;
        vertx[1] = x + rad * cos(sl);
        vertx[2] = x + rad * sqrt(2) * cos(sl + 45);
        vertx[3] = x - rad * cos(90 - sl);

        verty[0] = y;
        verty[1] = y + rad * sin(sl);
        verty[2] = y + rad * sqrt(2) * sin(sl + 45);
        verty[3] = y + rad * sin(90 - sl);

        for (int i = 0; i < initHeightMap.length; i++) {
            for (int j = 0; j < initHeightMap[0].length; j++) {
                if (in(j, i, vertx, verty) || in(j - w, i, vertx, verty) || in(j + w, i, vertx, verty)
                        || in(j, i+h, vertx, verty) || in(j, i-h, vertx, verty)
                        || in(j-w, i-h, vertx, verty) || in(j-w, i+h, vertx, verty)
                        || in(j+w, i-h, vertx, verty) || in(j+w, i+h, vertx, verty)) {
                    initHeightMap[i][j]++;
                }

            }
        }
    }

    private static boolean in(float x, float y, float[] polyX, float[] polyY) {
        int i, j = 4-1;
        boolean  oddNodes = false;

        for (i=0; i<4; i++) {
            if ((polyY[i]< y && polyY[j]>=y  || polyY[j]< y && polyY[i]>=y)
                    &&  (polyX[i]<=x || polyX[j]<=x)) {
                oddNodes ^= (polyX[i]+(y-polyY[i])/(polyY[j]-polyY[i])*(polyX[j]-polyX[i])<x);
            }
            j=i;
        }

        return oddNodes;
    }

    private static float sin(double theta) {
        return (float)Math.sin(theta/180*Math.PI);
    }

    private static float cos(double theta) {
        return (float)Math.cos(theta/180*Math.PI);
    }

    private static float sqrt(double v) {
        return (float)Math.sqrt(v);
    }

    public int getY() {
        return map.length;
    }

    public int getX() {
        return map[0].length;
    }

    public float get(int x, int y) {
        try {
            return map[y][x];
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static float max(float[][] heightmap) {
        float max = Integer.MIN_VALUE;

        for (int i = 0; i < heightmap.length; i++) {
            for (int j = 0; j < heightmap[0].length; j++) {
                max = Math.max(max, heightmap[i][j]);
            }
        }

        return max;
    }

    private static float min(float[][] heightmap) {
        float min = Integer.MAX_VALUE;

        for (int i = 0; i < heightmap.length; i++) {
            for (int j = 0; j < heightmap[0].length; j++) {
                min = Math.min(min, heightmap[i][j]);
            }
        }

        return min;
    }

    /*private static void step(float[][] heightmap, int y1, int x1, int y2, int x2) {
        if (y1 >= y2-1 && x1 >= x2-1) return;

        float avg = heightmap[y1][x1] + heightmap[y1][x2] + heightmap[y2][x1] + heightmap[y2][x2];
        avg /= 4f;

        int centerX = (x1 + x2) / 2;
        int centerY = (y1 + y2) / 2;

        heightmap[centerY][centerX] = avg + (float)INTL_NOISE.eval(centerX/10f, centerX/10f);

        heightmap[y1][centerX] = avg + (float)INTL_NOISE.eval(y1/10f, centerX/10f);
        heightmap[y2][centerX] = avg + (float)INTL_NOISE.eval(y2/10f, centerX/10f);
        heightmap[centerY][x1] = avg + (float)INTL_NOISE.eval(centerY/10f, x1/10f);
        heightmap[centerY][x2] = avg + (float)INTL_NOISE.eval(centerY/10f, x2/10f);

        step(heightmap, y1, x1, centerY, centerX);
        step(heightmap, centerY, x1, y2, centerX);
        step(heightmap, y1, centerX, centerY, x2);
        step(heightmap, centerY, centerX, y2, x2);
    }*/
}
