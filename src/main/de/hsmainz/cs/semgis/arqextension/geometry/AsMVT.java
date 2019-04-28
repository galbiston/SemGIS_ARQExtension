package de.hsmainz.cs.semgis.arqextension.geometry;

import java.nio.file.Paths;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.GeometryFactory;

import com.wdtinc.mapbox_vector_tile.adapt.jts.IGeometryFilter;
import com.wdtinc.mapbox_vector_tile.adapt.jts.JtsAdapter;
import com.wdtinc.mapbox_vector_tile.adapt.jts.MvtReader;
import com.wdtinc.mapbox_vector_tile.adapt.jts.TagKeyValueMapConverter;
import com.wdtinc.mapbox_vector_tile.adapt.jts.TileGeomResult;
import com.wdtinc.mapbox_vector_tile.adapt.jts.model.JtsMvt;
import com.wdtinc.mapbox_vector_tile.build.MvtLayerParams;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class AsMVT extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
        GeometryWrapper geometry = GeometryWrapper.extract(v);
		GeometryFactory geomFactory = new GeometryFactory();
		IGeometryFilter acceptAllGeomFilter = geometry.getXYGeometry() -> true;
		Envelope tileEnvelope = new Envelope(0d, 100d, 0d, 100d);
		MvtLayerParams layerParams = new MvtLayerParams(); // Default extent

		TileGeomResult tileGeom = JtsAdapter.createTileGeom(
		        geometry.getParsingGeometry(), // Your geometry
		        tileEnvelope,
		        geomFactory,
		        layerParams,
		        acceptAllGeomFilter);
		return NodeValue.makeString(tileGeom.toString());
	}

}
