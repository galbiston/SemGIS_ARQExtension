package de.hsmainz.cs.semgis.arqextension;

import de.hsmainz.cs.semgis.arqextension.envelope.MakeEnvelope;
import de.hsmainz.cs.semgis.arqextension.envelope.XMax;
import de.hsmainz.cs.semgis.arqextension.envelope.XMin;
import de.hsmainz.cs.semgis.arqextension.envelope.YMax;
import de.hsmainz.cs.semgis.arqextension.envelope.YMin;
import de.hsmainz.cs.semgis.arqextension.geometry.Area;
import de.hsmainz.cs.semgis.arqextension.geometry.AsBinary;
import de.hsmainz.cs.semgis.arqextension.geometry.AsGML;
import de.hsmainz.cs.semgis.arqextension.geometry.AsGeoJSON;
import de.hsmainz.cs.semgis.arqextension.geometry.AsKML;
import de.hsmainz.cs.semgis.arqextension.geometry.AsSVG;
import de.hsmainz.cs.semgis.arqextension.geometry.AsText;
import de.hsmainz.cs.semgis.arqextension.geometry.BoundingDiagonal;
import de.hsmainz.cs.semgis.arqextension.geometry.Centroid;
import de.hsmainz.cs.semgis.arqextension.geometry.ClosestPoint;
import de.hsmainz.cs.semgis.arqextension.geometry.ConcaveHull;
import de.hsmainz.cs.semgis.arqextension.geometry.DelaunayTriangles;
import de.hsmainz.cs.semgis.arqextension.geometry.Dimension;
import de.hsmainz.cs.semgis.arqextension.geometry.Distance3D;
import de.hsmainz.cs.semgis.arqextension.geometry.FlipCoordinates;
import de.hsmainz.cs.semgis.arqextension.geometry.GeomFromGeoJSON;
import de.hsmainz.cs.semgis.arqextension.geometry.GeomFromText;
import de.hsmainz.cs.semgis.arqextension.geometry.GeomFromWKB;
import de.hsmainz.cs.semgis.arqextension.geometry.GeometryN;
import de.hsmainz.cs.semgis.arqextension.geometry.GeometryType;
import de.hsmainz.cs.semgis.arqextension.geometry.HausdorffDistance;
import de.hsmainz.cs.semgis.arqextension.geometry.IsCollection;
import de.hsmainz.cs.semgis.arqextension.geometry.IsValidReason;
import de.hsmainz.cs.semgis.arqextension.geometry.Length;
import de.hsmainz.cs.semgis.arqextension.geometry.MinimumBoundingCircle;
import de.hsmainz.cs.semgis.arqextension.geometry.MinimumBoundingRadius;
import de.hsmainz.cs.semgis.arqextension.geometry.MinimumClearance;
import de.hsmainz.cs.semgis.arqextension.geometry.MinimumClearanceLine;
import de.hsmainz.cs.semgis.arqextension.geometry.Normalize;
import de.hsmainz.cs.semgis.arqextension.geometry.NumGeometries;
import de.hsmainz.cs.semgis.arqextension.geometry.NumPoints;
import de.hsmainz.cs.semgis.arqextension.geometry.OrderingEquals;
import de.hsmainz.cs.semgis.arqextension.geometry.Perimeter;
import de.hsmainz.cs.semgis.arqextension.geometry.PointN;
import de.hsmainz.cs.semgis.arqextension.geometry.Reverse;
import de.hsmainz.cs.semgis.arqextension.geometry.Rotate;
import de.hsmainz.cs.semgis.arqextension.geometry.Scale;
import de.hsmainz.cs.semgis.arqextension.geometry.Simplify;
import de.hsmainz.cs.semgis.arqextension.geometry.SimplifyPreserveTopology;
import de.hsmainz.cs.semgis.arqextension.geometry.SimplifyVW;
import de.hsmainz.cs.semgis.arqextension.geometry.Snap;
import de.hsmainz.cs.semgis.arqextension.geometry.TransScale;
import de.hsmainz.cs.semgis.arqextension.geometry.Transform;
import de.hsmainz.cs.semgis.arqextension.geometry.Translate;
import de.hsmainz.cs.semgis.arqextension.geometry.UnaryUnion;
import de.hsmainz.cs.semgis.arqextension.linestring.AddPoint;
import de.hsmainz.cs.semgis.arqextension.linestring.EndPoint;
import de.hsmainz.cs.semgis.arqextension.linestring.IsClosed;
import de.hsmainz.cs.semgis.arqextension.linestring.IsRing;
import de.hsmainz.cs.semgis.arqextension.linestring.LineFromText;
import de.hsmainz.cs.semgis.arqextension.linestring.LineFromWKB;
import de.hsmainz.cs.semgis.arqextension.linestring.MakeLine;
import de.hsmainz.cs.semgis.arqextension.linestring.RemovePoint;
import de.hsmainz.cs.semgis.arqextension.linestring.Segmentize;
import de.hsmainz.cs.semgis.arqextension.linestring.StartPoint;
import de.hsmainz.cs.semgis.arqextension.point.Azimuth;
import de.hsmainz.cs.semgis.arqextension.point.M;
import de.hsmainz.cs.semgis.arqextension.point.MakePointM;
import de.hsmainz.cs.semgis.arqextension.point.PointFromGeoHash;
import de.hsmainz.cs.semgis.arqextension.point.PointFromText;
import de.hsmainz.cs.semgis.arqextension.point.PointFromWKB;
import de.hsmainz.cs.semgis.arqextension.point.PointInsideCircle;
import de.hsmainz.cs.semgis.arqextension.point.X;
import de.hsmainz.cs.semgis.arqextension.point.Y;
import de.hsmainz.cs.semgis.arqextension.point.Z;
import de.hsmainz.cs.semgis.arqextension.polygon.InteriorRingN;
import de.hsmainz.cs.semgis.arqextension.polygon.IsConvex;
import de.hsmainz.cs.semgis.arqextension.polygon.MakePolygon;
import de.hsmainz.cs.semgis.arqextension.polygon.NRings;
import de.hsmainz.cs.semgis.arqextension.polygon.NumInteriorRings;
import de.hsmainz.cs.semgis.arqextension.raster.Band;
import de.hsmainz.cs.semgis.arqextension.raster.BandMetaData;
import de.hsmainz.cs.semgis.arqextension.raster.BandNoDataValue;
import de.hsmainz.cs.semgis.arqextension.raster.BandPixelType;
import de.hsmainz.cs.semgis.arqextension.raster.Clip;
import de.hsmainz.cs.semgis.arqextension.raster.HasNoBand;
import de.hsmainz.cs.semgis.arqextension.raster.Height;
import de.hsmainz.cs.semgis.arqextension.raster.MemSize;
import de.hsmainz.cs.semgis.arqextension.raster.NearestValue;
import de.hsmainz.cs.semgis.arqextension.raster.NumBands;
import de.hsmainz.cs.semgis.arqextension.raster.PixelAsPoint;
import de.hsmainz.cs.semgis.arqextension.raster.PixelHeight;
import de.hsmainz.cs.semgis.arqextension.raster.RasterToWorldCoord;
import de.hsmainz.cs.semgis.arqextension.raster.RasterToWorldCoordX;
import de.hsmainz.cs.semgis.arqextension.raster.RasterToWorldCoordY;
import de.hsmainz.cs.semgis.arqextension.raster.Summary;
import de.hsmainz.cs.semgis.arqextension.raster.UpperLeftX;
import de.hsmainz.cs.semgis.arqextension.raster.UpperLeftY;
import de.hsmainz.cs.semgis.arqextension.raster.Width;
import de.hsmainz.cs.semgis.arqextension.raster.WorldToRasterCoord;
import de.hsmainz.cs.semgis.arqextension.raster.WorldToRasterCoordX;
import de.hsmainz.cs.semgis.arqextension.raster.WorldToRasterCoordY;
import de.hsmainz.cs.semgis.arqextension.vocabulary.PostGISGeo;
import io.github.galbiston.geosparql_jena.configuration.GeoSPARQLConfig;
import io.github.galbiston.geosparql_jena.geof.nontopological.filter_functions.GetSRIDFF;
import io.github.galbiston.geosparql_jena.geof.topological.filter_functions.geometry_property.IsEmptyFF;
import io.github.galbiston.geosparql_jena.geof.topological.filter_functions.geometry_property.IsSimpleFF;
import io.github.galbiston.geosparql_jena.geof.topological.filter_functions.geometry_property.IsValidFF;
import org.apache.jena.sparql.function.FunctionRegistry;

public class PostGISConfig {

    private static Boolean IS_FUNCTIONS_REGISTERED = false;

    public static final void setup() {

        //Only register functions once.
        if (!IS_FUNCTIONS_REGISTERED) {
            FunctionRegistry functionRegistry = FunctionRegistry.get();

            //POSTGIS functionRegistry
            functionRegistry.put(PostGISGeo.st_addpoint.getURI(), AddPoint.class);
            functionRegistry.put(PostGISGeo.st_asbinary.getURI(), AsBinary.class);
            functionRegistry.put(PostGISGeo.st_askml.getURI(), AsKML.class);
            functionRegistry.put(PostGISGeo.st_asgml.getURI(), AsGML.class);
            functionRegistry.put(PostGISGeo.st_asgeojson.getURI(), AsGeoJSON.class);
            functionRegistry.put(PostGISGeo.st_assvg.getURI(), AsSVG.class);
            functionRegistry.put(PostGISGeo.st_astext.getURI(), AsText.class);
            functionRegistry.put(PostGISGeo.st_area.getURI(), Area.class);
            functionRegistry.put(PostGISGeo.st_azimuth.getURI(), Azimuth.class);
            functionRegistry.put(PostGISGeo.st_band.getURI(), Band.class);
            functionRegistry.put(PostGISGeo.st_bandmetadata.getURI(), BandMetaData.class);
            functionRegistry.put(PostGISGeo.st_bandnodatavalue.getURI(), BandNoDataValue.class);
            functionRegistry.put(PostGISGeo.st_bandpixeltype.getURI(), BandPixelType.class);
            functionRegistry.put(PostGISGeo.st_boundingdiagonal.getURI(), BoundingDiagonal.class);
            functionRegistry.put(PostGISGeo.st_centroid.getURI(), Centroid.class);
            functionRegistry.put(PostGISGeo.st_clip.getURI(), Clip.class);
            functionRegistry.put(PostGISGeo.st_closestPoint.getURI(), ClosestPoint.class);
            functionRegistry.put(PostGISGeo.st_concaveHull.getURI(), ConcaveHull.class);
            functionRegistry.put(PostGISGeo.st_delaunayTriangles.getURI(), DelaunayTriangles.class);
            functionRegistry.put(PostGISGeo.st_dimension.getURI(), Dimension.class);
            functionRegistry.put(PostGISGeo.st_distance3d.getURI(), Distance3D.class);
            functionRegistry.put(PostGISGeo.st_endPoint.getURI(), EndPoint.class);
            functionRegistry.put(PostGISGeo.st_flipCoordinates.getURI(), FlipCoordinates.class);
            functionRegistry.put(PostGISGeo.st_geomFromGeoJSON.getURI(), GeomFromGeoJSON.class);
            functionRegistry.put(PostGISGeo.st_geomFromWKB.getURI(), GeomFromWKB.class);
            functionRegistry.put(PostGISGeo.st_geomFromText.getURI(), GeomFromText.class);
            functionRegistry.put(PostGISGeo.st_geometryN.getURI(), GeometryN.class);
            functionRegistry.put(PostGISGeo.st_geometryType.getURI(), GeometryType.class);
            functionRegistry.put(PostGISGeo.st_hasNoBand.getURI(), HasNoBand.class);
            functionRegistry.put(PostGISGeo.st_height.getURI(), Height.class);
            functionRegistry.put(PostGISGeo.st_hausdorffDistance.getURI(), HausdorffDistance.class);
            functionRegistry.put(PostGISGeo.st_interiorRingN.getURI(), InteriorRingN.class);
            functionRegistry.put(PostGISGeo.st_isCollection.getURI(), IsCollection.class);
            functionRegistry.put(PostGISGeo.st_isClosed.getURI(), IsClosed.class);
            functionRegistry.put(PostGISGeo.st_isConvex.getURI(), IsConvex.class);
            functionRegistry.put(PostGISGeo.st_isEmpty.getURI(), IsEmptyFF.class);
            functionRegistry.put(PostGISGeo.st_isRing.getURI(), IsRing.class);
            functionRegistry.put(PostGISGeo.st_isSimple.getURI(), IsSimpleFF.class);
            functionRegistry.put(PostGISGeo.st_isValid.getURI(), IsValidFF.class);
            functionRegistry.put(PostGISGeo.st_isValidReason.getURI(), IsValidReason.class);
            functionRegistry.put(PostGISGeo.st_Length.getURI(), Length.class);
            functionRegistry.put(PostGISGeo.st_Length2D.getURI(), Length.class);
            functionRegistry.put(PostGISGeo.st_lineFromWKB.getURI(), LineFromWKB.class);
            functionRegistry.put(PostGISGeo.st_lineFromText.getURI(), LineFromText.class);
            functionRegistry.put(PostGISGeo.st_m.getURI(), M.class);
            functionRegistry.put(PostGISGeo.st_makeEnvelope.getURI(), MakeEnvelope.class);
            functionRegistry.put(PostGISGeo.st_makeLine.getURI(), MakeLine.class);
            functionRegistry.put(PostGISGeo.st_makePointM.getURI(), MakePointM.class);
            functionRegistry.put(PostGISGeo.st_makePolygon.getURI(), MakePolygon.class);
            functionRegistry.put(PostGISGeo.st_minimumBoundingCircle.getURI(), MinimumBoundingCircle.class);
            functionRegistry.put(PostGISGeo.st_minimumBoundingRadius.getURI(), MinimumBoundingRadius.class);
            functionRegistry.put(PostGISGeo.st_minimumClearance.getURI(), MinimumClearance.class);
            functionRegistry.put(PostGISGeo.st_minimumClearanceLine.getURI(), MinimumClearanceLine.class);
            functionRegistry.put(PostGISGeo.st_memsize.getURI(), MemSize.class);
            functionRegistry.put(PostGISGeo.st_nearestValue.getURI(), NearestValue.class);
            functionRegistry.put(PostGISGeo.st_numBands.getURI(), NumBands.class);
            functionRegistry.put(PostGISGeo.st_numGeometries.getURI(), NumGeometries.class);
            functionRegistry.put(PostGISGeo.st_numInteriorRings.getURI(), NumInteriorRings.class);
            functionRegistry.put(PostGISGeo.st_numPoints.getURI(), NumPoints.class);
            functionRegistry.put(PostGISGeo.st_nPoints.getURI(), NumPoints.class);
            functionRegistry.put(PostGISGeo.st_nRings.getURI(), NRings.class);
            functionRegistry.put(PostGISGeo.st_normalize.getURI(), Normalize.class);
            functionRegistry.put(PostGISGeo.st_offsetCurve.getURI(), OffsetCurve.class);
            functionRegistry.put(PostGISGeo.st_orderingEquals.getURI(), OrderingEquals.class);
            functionRegistry.put(PostGISGeo.st_perimeter.getURI(), Perimeter.class);
            functionRegistry.put(PostGISGeo.st_perimeter2D.getURI(), Perimeter.class);
            functionRegistry.put(PostGISGeo.st_pixelAsPoint.getURI(), PixelAsPoint.class);
            functionRegistry.put(PostGISGeo.st_pixelHeight.getURI(), PixelHeight.class);
            functionRegistry.put(PostGISGeo.st_pointN.getURI(), PointN.class);
            functionRegistry.put(PostGISGeo.st_pointFromGeoHash.getURI(), PointFromGeoHash.class);
            functionRegistry.put(PostGISGeo.st_pointFromWKB.getURI(), PointFromWKB.class);
            functionRegistry.put(PostGISGeo.st_pointFromText.getURI(), PointFromText.class);
            functionRegistry.put(PostGISGeo.st_pointInsideCircle.getURI(), PointInsideCircle.class);
            functionRegistry.put(PostGISGeo.st_rast_isEmpty.getURI(), de.hsmainz.cs.semgis.arqextension.raster.IsEmpty.class);
            functionRegistry.put(PostGISGeo.st_rast_Contains.getURI(), de.hsmainz.cs.semgis.arqextension.raster.Contains.class);
            functionRegistry.put(PostGISGeo.st_rast_Covers.getURI(), de.hsmainz.cs.semgis.arqextension.raster.Covers.class);
            functionRegistry.put(PostGISGeo.st_rast_CoveredBy.getURI(), de.hsmainz.cs.semgis.arqextension.raster.CoveredBy.class);
            functionRegistry.put(PostGISGeo.st_rast_Disjoint.getURI(), de.hsmainz.cs.semgis.arqextension.raster.Disjoint.class);
            functionRegistry.put(PostGISGeo.st_rast_Intersects.getURI(), de.hsmainz.cs.semgis.arqextension.raster.Intersects.class);
            functionRegistry.put(PostGISGeo.st_rast_Overlaps.getURI(), de.hsmainz.cs.semgis.arqextension.raster.Overlaps.class);
            functionRegistry.put(PostGISGeo.st_rast_srid.getURI(), de.hsmainz.cs.semgis.arqextension.raster.SRID.class);
            functionRegistry.put(PostGISGeo.st_rast_Touches.getURI(), de.hsmainz.cs.semgis.arqextension.raster.Touches.class);
            functionRegistry.put(PostGISGeo.st_rast_Within.getURI(), de.hsmainz.cs.semgis.arqextension.raster.Within.class);
            functionRegistry.put(PostGISGeo.st_rasterToWorldCoord.getURI(), RasterToWorldCoord.class);
            functionRegistry.put(PostGISGeo.st_rasterToWorldCoordX.getURI(), RasterToWorldCoordX.class);
            functionRegistry.put(PostGISGeo.st_rasterToWorldCoordY.getURI(), RasterToWorldCoordY.class);
            functionRegistry.put(PostGISGeo.st_removePoint.getURI(), RemovePoint.class);
            functionRegistry.put(PostGISGeo.st_reverse.getURI(), Reverse.class);
            functionRegistry.put(PostGISGeo.st_rotate.getURI(), Rotate.class);
            functionRegistry.put(PostGISGeo.st_scale.getURI(), Scale.class);
            functionRegistry.put(PostGISGeo.st_segmentize.getURI(), Segmentize.class);
            functionRegistry.put(PostGISGeo.st_setPoint.getURI(), SetPoint.class);
            functionRegistry.put(PostGISGeo.st_simplify.getURI(), Simplify.class);
            functionRegistry.put(PostGISGeo.st_simplifyPreserveTopology.getURI(), SimplifyPreserveTopology.class);
            functionRegistry.put(PostGISGeo.st_simplifyVW.getURI(), SimplifyVW.class);
            functionRegistry.put(PostGISGeo.st_snap.getURI(), Snap.class);
            functionRegistry.put(PostGISGeo.st_split.getURI(), Split.class);
            functionRegistry.put(PostGISGeo.st_srid.getURI(), GetSRIDFF.class);
            functionRegistry.put(PostGISGeo.st_startPoint.getURI(), StartPoint.class);
            functionRegistry.put(PostGISGeo.st_summary.getURI(), Summary.class);
            functionRegistry.put(PostGISGeo.st_swapOrdinates.getURI(), SwapOrdinates.class);
            functionRegistry.put(PostGISGeo.st_transscale.getURI(), TransScale.class);
            functionRegistry.put(PostGISGeo.st_translate.getURI(), Translate.class);
            functionRegistry.put(PostGISGeo.st_transform.getURI(), Transform.class);
            functionRegistry.put(PostGISGeo.st_upperLeftX.getURI(), UpperLeftX.class);
            functionRegistry.put(PostGISGeo.st_upperLeftY.getURI(), UpperLeftY.class);
            functionRegistry.put(PostGISGeo.st_unaryUnion.getURI(), UnaryUnion.class);
            functionRegistry.put(PostGISGeo.st_width.getURI(), Width.class);
            functionRegistry.put(PostGISGeo.st_worldToRasterCoord.getURI(), WorldToRasterCoord.class);
            functionRegistry.put(PostGISGeo.st_worldToRasterCoordX.getURI(), WorldToRasterCoordX.class);
            functionRegistry.put(PostGISGeo.st_worldToRasterCoordY.getURI(), WorldToRasterCoordY.class);
            functionRegistry.put(PostGISGeo.st_unaryUnion.getURI(), UnaryUnion.class);
            functionRegistry.put(PostGISGeo.st_x.getURI(), X.class);
            functionRegistry.put(PostGISGeo.st_xMin.getURI(), XMin.class);
            functionRegistry.put(PostGISGeo.st_xMax.getURI(), XMax.class);
            functionRegistry.put(PostGISGeo.st_y.getURI(), Y.class);
            functionRegistry.put(PostGISGeo.st_yMin.getURI(), YMin.class);
            functionRegistry.put(PostGISGeo.st_yMax.getURI(), YMax.class);
            functionRegistry.put(PostGISGeo.st_z.getURI(), Z.class);

            // extra utility functionRegistry
            functionRegistry.put(Constants.SPATIAL_FUNCTION_NS + "transform", Transform.class);
            //functionRegistry.put(Constants.SPATIAL_FUNCTION_NS + "makeWKTPoint", CreateWKTPoint.class);
            //functionRegistry.put(Constants.SPATIAL_FUNCTION_NS + "WKTToGeometryPoint", LiteralToGeometryType.class);

            GeoSPARQLConfig.setupMemoryIndex();
            IS_FUNCTIONS_REGISTERED = true;
        }
    }
}
