package io.github.galbiston.geosparql_jena.implementation.datatype.raster;

import java.io.IOException;

import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.geotoolkit.coverage.wkb.WKBRasterReader;
import org.geotoolkit.coverage.wkb.WKBRasterWriter;
import org.locationtech.jts.io.WKBReader;
import org.locationtech.jts.io.WKBWriter;
import org.opengis.util.FactoryException;

import de.hsmainz.cs.semgis.arqextension.vocabulary.PostGISGeo;
import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;

public class HexWKBRastDatatype extends RasterDataType {

	public static final String URI = PostGISGeo.HexWKBRaster;
	
	public static final HexWKBRastDatatype INSTANCE =new HexWKBRastDatatype();
	
	public HexWKBRastDatatype() {
		super(URI);
	}
	
	@Override
	public String unparse(Object geometry) {
		if (geometry instanceof CoverageWrapper) {
            CoverageWrapper geometryWrapper = (CoverageWrapper) geometry;
            WKBRasterWriter writer=new WKBRasterWriter();
			String rasterWKB;
			try {
				rasterWKB = WKBWriter.toHex(writer.write(geometryWrapper.getXYGeometry())).toString();
				return rasterWKB.toString();
			} catch (IOException | FactoryException e) {
				throw new AssertionError(e.getMessage());
			}

        } else {
            throw new AssertionError("Object passed to HexWKBRasterDatatype is not a GeometryWrapper: " + geometry);
        }
	}

	@Override
	public CoverageWrapper read(String geometryLiteral) {
		WKBRasterReader reader2=new WKBRasterReader();
		GridCoverage2D coverage=reader2.readCoverage(WKBReader.hexToBytes(geometryLiteral), authorityFactory);
		return new CoverageWrapper(coverage, URI);
	}
	
    @Override
    public String toString() {
        return "HexWKBRasterDatatype{" + URI + '}';
    }
}