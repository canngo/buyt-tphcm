package vn.spidersoft.buyt;

import java.lang.reflect.Field;
import java.util.HashMap;

import android.graphics.Point;

import jp.co.mapion.android.maps.GeoPoint;
import jp.co.mapion.android.maps.Map;
import jp.co.mapion.android.maps.Tile;

public class BuytTPHCMMap extends Map {

        protected int tileWidth = 256;
        protected int tileHeight = 256;

        protected HashMap<Integer, Float> ratios = new HashMap<Integer, Float>();

        private HashMap<String, Integer> tileNameMap = new HashMap<String, Integer>();

        private String key;

        private int noimage;

        public BuytTPHCMMap(String key, int noimage) {
                this.key = key;
                this.noimage = noimage;
                init();
        }

        protected void init() {
//                ratios.put(1, 4.0f);
//                ratios.put(2, 2.0f);
//                ratios.put(3, 1.0f);
        	
            ratios.put(1, 32.0f);
            ratios.put(2, 16.0f);
            ratios.put(3, 8.0f);
            ratios.put(4, 4.0f);
            ratios.put(5, 2.0f);
            ratios.put(6, 1.0f);
        }

        @Override
        protected int getMaxZoomLevel() {
                return ratios.size();
        }

        @Override
        protected void setup() {
                setCenter(new GeoPoint(0, 0));
                setZoom(1);
        }

        @Override
        protected int getTileWidth() {
                return tileWidth;
        }

        @Override
        protected int getTileHeight() {
                return tileHeight;
        }

        @Override
        protected Point geoToPixel(GeoPoint geo) {
                int x = (int) (geo.getLongitudeE6() / getRatio());
                int y = (int) (geo.getLatitudeE6() / getRatio());
                return new Point(x, y);
        }

        @Override
        protected GeoPoint pixelToGeo(Point pixel) {
                int lat = (int) (pixel.y * getRatio());
                int lon = (int) (pixel.x * getRatio());
                return new GeoPoint(lat, lon);
        }

        @Override
        protected GeoPoint getOrigin() {
                double originRatio = ratios.get(1);
                int lat = (int) ((tileHeight / 2) * originRatio);
                int lon = (int) (-(tileWidth / 2) * originRatio);
                return new GeoPoint(lat, lon);
        }

        @Override
        protected String getURL(Tile tile) {
                int x = (int) tile.getX();
                int y = (int) tile.getY();
                if (x < 0 || y < 0) {
                        return noMap();
                }
                if (isOut(tile)) {
                        return noMap();
                }
                int gzoom = getZoom() - 1;
                double pow = Math.pow(2, gzoom);
                StringBuilder tileName = new StringBuilder();
                tileName.append("t");
                for (int i = 0; i < gzoom; i++) {
                        pow = pow / 2;
                        if (y < pow) {
                                if (x < pow) {
                                        tileName.append("q");
                                } else {
                                        tileName.append("r");
                                        x -= pow;
                                }
                        } else {
                                if (x < pow) {
                                        tileName.append("t");
                                        y -= pow;
                                } else {
                                        tileName.append("s");
                                        x -= pow;
                                        y -= pow;
                                }
                        }
                }
                return String.valueOf(getResourceInt(tileName.toString()));
        }

        @Override
        protected String getKey() {
                return key;
        }

        private double getRatio() {
                return ratios.get(getZoom());
        }

        private int getResourceInt(String name) {
                if (tileNameMap.containsKey(name)) {
                        return tileNameMap.get(name);
                }
                try {
                        Field field = R.drawable.class.getDeclaredField(name);
                        int tileId = field.getInt(R.drawable.class);
                        tileNameMap.put(name, tileId);
                        return tileId;
                } catch (Exception e) {
                }
                return -1;
        }

        private String noMap() {
                return String.valueOf(noimage);
        }

        private boolean isOut(Tile tile) {
                int max = 1 << (getZoom() - 1);
                if (tile.getX() >= max || tile.getY() >= max) {
                        return true;
                }
                return false;
        }
}