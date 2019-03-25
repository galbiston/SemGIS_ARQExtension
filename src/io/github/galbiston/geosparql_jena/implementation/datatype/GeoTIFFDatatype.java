package io.github.galbiston.geosparql_jena.implementation.datatype;

import org.geotoolkit.coverage.grid.GridCoverage;
import org.geotoolkit.coverage.io.CoverageIO;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class GeoTIFFDatatype extends GeometryDatatype {

	@Override
	public GeometryWrapper read(String geometryLiteral) {
		final GridCoverage coverage = CoverageIO.read(geometryLiteral);
		return new GeometryWrapper(coverage);
		
	}

}
