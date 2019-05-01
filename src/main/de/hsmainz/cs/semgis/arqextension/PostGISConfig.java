package de.hsmainz.cs.semgis.arqextension;

import de.hsmainz.cs.semgis.arqextension.envelope.MakeEnvelope;
import de.hsmainz.cs.semgis.arqextension.envelope.XMax;
import de.hsmainz.cs.semgis.arqextension.envelope.XMin;
import de.hsmainz.cs.semgis.arqextension.envelope.YMax;
import de.hsmainz.cs.semgis.arqextension.envelope.YMin;
import de.hsmainz.cs.semgis.arqextension.envelope.ZMax;
import de.hsmainz.cs.semgis.arqextension.envelope.ZMin;
import de.hsmainz.cs.semgis.arqextension.envelope.Zmflag;
import de.hsmainz.cs.semgis.arqextension.geometry.Area;
import de.hsmainz.cs.semgis.arqextension.geometry.AsBinary;
import de.hsmainz.cs.semgis.arqextension.geometry.AsGML;
import de.hsmainz.cs.semgis.arqextension.geometry.AsGeoBuf;
import de.hsmainz.cs.semgis.arqextension.geometry.AsGeoJSON;
import de.hsmainz.cs.semgis.arqextension.geometry.AsHEXEWKB;
import de.hsmainz.cs.semgis.arqextension.geometry.AsKML;
import de.hsmainz.cs.semgis.arqextension.geometry.AsMVT;
import de.hsmainz.cs.semgis.arqextension.geometry.AsSVG;
import de.hsmainz.cs.semgis.arqextension.geometry.AsText;
import de.hsmainz.cs.semgis.arqextension.geometry.Boundary;
import de.hsmainz.cs.semgis.arqextension.geometry.BoundingDiagonal;
import de.hsmainz.cs.semgis.arqextension.geometry.Centroid;
import de.hsmainz.cs.semgis.arqextension.geometry.ChaikinSmoothing;
import de.hsmainz.cs.semgis.arqextension.geometry.ClosestPoint;
import de.hsmainz.cs.semgis.arqextension.geometry.ClosestPoint3D;
import de.hsmainz.cs.semgis.arqextension.geometry.ClusterIntersecting;
import de.hsmainz.cs.semgis.arqextension.geometry.ClusterKMeans;
import de.hsmainz.cs.semgis.arqextension.geometry.ClusterWithin;
import de.hsmainz.cs.semgis.arqextension.geometry.CollectionExtract;
import de.hsmainz.cs.semgis.arqextension.geometry.CollectionHomogenize;
import de.hsmainz.cs.semgis.arqextension.geometry.ConcaveHull;
import de.hsmainz.cs.semgis.arqextension.geometry.DWithin3D;
import de.hsmainz.cs.semgis.arqextension.geometry.DelaunayTriangles;
import de.hsmainz.cs.semgis.arqextension.geometry.Dimension;
import de.hsmainz.cs.semgis.arqextension.geometry.Distance3D;
import de.hsmainz.cs.semgis.arqextension.geometry.DistanceSphere;
import de.hsmainz.cs.semgis.arqextension.geometry.FilterByM;
import de.hsmainz.cs.semgis.arqextension.geometry.FlipCoordinates;
import de.hsmainz.cs.semgis.arqextension.geometry.Force2D;
import de.hsmainz.cs.semgis.arqextension.geometry.Force3D;
import de.hsmainz.cs.semgis.arqextension.geometry.Force3DM;
import de.hsmainz.cs.semgis.arqextension.geometry.Force4D;
import de.hsmainz.cs.semgis.arqextension.geometry.ForceCollection;
import de.hsmainz.cs.semgis.arqextension.geometry.GeomFromGML;
import de.hsmainz.cs.semgis.arqextension.geometry.GeomFromGeoJSON;
import de.hsmainz.cs.semgis.arqextension.geometry.GeomFromKML;
import de.hsmainz.cs.semgis.arqextension.geometry.GeomFromText;
import de.hsmainz.cs.semgis.arqextension.geometry.GeomFromWKB;
import de.hsmainz.cs.semgis.arqextension.geometry.GeometryN;
import de.hsmainz.cs.semgis.arqextension.geometry.GeometryType;
import de.hsmainz.cs.semgis.arqextension.geometry.HasRepeatedPoints;
import de.hsmainz.cs.semgis.arqextension.geometry.HausdorffDistance;
import de.hsmainz.cs.semgis.arqextension.geometry.IsCollection;
import de.hsmainz.cs.semgis.arqextension.geometry.IsValidReason;
import de.hsmainz.cs.semgis.arqextension.geometry.Length;
import de.hsmainz.cs.semgis.arqextension.geometry.LocateAlong;
import de.hsmainz.cs.semgis.arqextension.geometry.LocateBetween;
import de.hsmainz.cs.semgis.arqextension.geometry.LocateBetweenElevations;
import de.hsmainz.cs.semgis.arqextension.geometry.LongestLine;
import de.hsmainz.cs.semgis.arqextension.geometry.LongestLine3D;
import de.hsmainz.cs.semgis.arqextension.geometry.MaxDistance;
import de.hsmainz.cs.semgis.arqextension.geometry.MaxDistance3D;
import de.hsmainz.cs.semgis.arqextension.geometry.MinimumBoundingCircle;
import de.hsmainz.cs.semgis.arqextension.geometry.MinimumBoundingRadius;
import de.hsmainz.cs.semgis.arqextension.geometry.MinimumClearance;
import de.hsmainz.cs.semgis.arqextension.geometry.MinimumClearanceLine;
import de.hsmainz.cs.semgis.arqextension.geometry.Multi;
import de.hsmainz.cs.semgis.arqextension.geometry.Normalize;
import de.hsmainz.cs.semgis.arqextension.geometry.NumGeometries;
import de.hsmainz.cs.semgis.arqextension.geometry.NumPoints;
import de.hsmainz.cs.semgis.arqextension.geometry.OrderingEquals;
import de.hsmainz.cs.semgis.arqextension.geometry.PatchN;
import de.hsmainz.cs.semgis.arqextension.geometry.Perimeter;
import de.hsmainz.cs.semgis.arqextension.geometry.PointN;
import de.hsmainz.cs.semgis.arqextension.geometry.PointOnSurface;
import de.hsmainz.cs.semgis.arqextension.geometry.RemoveRepeatedPoints;
import de.hsmainz.cs.semgis.arqextension.geometry.Reverse;
import de.hsmainz.cs.semgis.arqextension.geometry.Rotate;
import de.hsmainz.cs.semgis.arqextension.geometry.RotateX;
import de.hsmainz.cs.semgis.arqextension.geometry.RotateY;
import de.hsmainz.cs.semgis.arqextension.geometry.RotateZ;
import de.hsmainz.cs.semgis.arqextension.geometry.Scale;
import de.hsmainz.cs.semgis.arqextension.geometry.ShortestLine3D;
import de.hsmainz.cs.semgis.arqextension.geometry.Simplify;
import de.hsmainz.cs.semgis.arqextension.geometry.SimplifyPreserveTopology;
import de.hsmainz.cs.semgis.arqextension.geometry.SimplifyVW;
import de.hsmainz.cs.semgis.arqextension.geometry.Snap;
import de.hsmainz.cs.semgis.arqextension.geometry.Split;
import de.hsmainz.cs.semgis.arqextension.geometry.SwapOrdinates;
import de.hsmainz.cs.semgis.arqextension.geometry.TransScale;
import de.hsmainz.cs.semgis.arqextension.geometry.Transform;
import de.hsmainz.cs.semgis.arqextension.geometry.Translate;
import de.hsmainz.cs.semgis.arqextension.geometry.UnaryUnion;
import de.hsmainz.cs.semgis.arqextension.geometry.VoronoiLines;
import de.hsmainz.cs.semgis.arqextension.geometry.VoronoiPolygons;
import de.hsmainz.cs.semgis.arqextension.linestring.AddPoint;
import de.hsmainz.cs.semgis.arqextension.linestring.AsEncodedPolyline;
import de.hsmainz.cs.semgis.arqextension.linestring.EndPoint;
import de.hsmainz.cs.semgis.arqextension.linestring.IsClosed;
import de.hsmainz.cs.semgis.arqextension.linestring.IsRing;
import de.hsmainz.cs.semgis.arqextension.linestring.IsValidTrajectory;
import de.hsmainz.cs.semgis.arqextension.linestring.LineCrossingDirection;
import de.hsmainz.cs.semgis.arqextension.linestring.LineFromEncodedPolyline;
import de.hsmainz.cs.semgis.arqextension.linestring.LineFromMultiPoint;
import de.hsmainz.cs.semgis.arqextension.linestring.LineFromText;
import de.hsmainz.cs.semgis.arqextension.linestring.LineFromWKB;
import de.hsmainz.cs.semgis.arqextension.linestring.LineMerge;
import de.hsmainz.cs.semgis.arqextension.linestring.LineSubstring;
import de.hsmainz.cs.semgis.arqextension.linestring.MLineFromText;
import de.hsmainz.cs.semgis.arqextension.linestring.MakeLine;
import de.hsmainz.cs.semgis.arqextension.linestring.OffsetCurve;
import de.hsmainz.cs.semgis.arqextension.linestring.RemovePoint;
import de.hsmainz.cs.semgis.arqextension.linestring.Segmentize;
import de.hsmainz.cs.semgis.arqextension.linestring.StartPoint;
import de.hsmainz.cs.semgis.arqextension.point.Azimuth;
import de.hsmainz.cs.semgis.arqextension.point.GeometricMedian;
import de.hsmainz.cs.semgis.arqextension.point.M;
import de.hsmainz.cs.semgis.arqextension.point.MPointFromText;
import de.hsmainz.cs.semgis.arqextension.point.MakePoint;
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
import de.hsmainz.cs.semgis.arqextension.polygon.IsPolygonCCW;
import de.hsmainz.cs.semgis.arqextension.polygon.IsPolygonCW;
import de.hsmainz.cs.semgis.arqextension.polygon.MPolyFromText;
import de.hsmainz.cs.semgis.arqextension.polygon.MakePolygon;
import de.hsmainz.cs.semgis.arqextension.polygon.NRings;
import de.hsmainz.cs.semgis.arqextension.polygon.NumInteriorRings;
import de.hsmainz.cs.semgis.arqextension.polygon.Orientation;
import de.hsmainz.cs.semgis.arqextension.raster.AddBand;
import de.hsmainz.cs.semgis.arqextension.raster.AsJPG;
import de.hsmainz.cs.semgis.arqextension.raster.AsPNG;
import de.hsmainz.cs.semgis.arqextension.raster.AsTIFF;
import de.hsmainz.cs.semgis.arqextension.raster.Band;
import de.hsmainz.cs.semgis.arqextension.raster.BandMetaData;
import de.hsmainz.cs.semgis.arqextension.raster.BandNoDataValue;
import de.hsmainz.cs.semgis.arqextension.raster.BandPixelType;
import de.hsmainz.cs.semgis.arqextension.raster.Clip;
import de.hsmainz.cs.semgis.arqextension.raster.Count;
import de.hsmainz.cs.semgis.arqextension.raster.HasNoBand;
import de.hsmainz.cs.semgis.arqextension.raster.Height;
import de.hsmainz.cs.semgis.arqextension.raster.MakeEmptyCoverage;
import de.hsmainz.cs.semgis.arqextension.raster.MakeEmptyRaster;
import de.hsmainz.cs.semgis.arqextension.raster.MemSize;
import de.hsmainz.cs.semgis.arqextension.raster.MinConvexHull;
import de.hsmainz.cs.semgis.arqextension.raster.NearestValue;
import de.hsmainz.cs.semgis.arqextension.raster.NumBands;
import de.hsmainz.cs.semgis.arqextension.raster.PixelAsPoint;
import de.hsmainz.cs.semgis.arqextension.raster.PixelAsPolygon;
import de.hsmainz.cs.semgis.arqextension.raster.PixelHeight;
import de.hsmainz.cs.semgis.arqextension.raster.PixelWidth;
import de.hsmainz.cs.semgis.arqextension.raster.RastFromHexWKB;
import de.hsmainz.cs.semgis.arqextension.raster.RastFromWKB;
import de.hsmainz.cs.semgis.arqextension.raster.RasterToWorldCoord;
import de.hsmainz.cs.semgis.arqextension.raster.RasterToWorldCoordX;
import de.hsmainz.cs.semgis.arqextension.raster.RasterToWorldCoordY;
import de.hsmainz.cs.semgis.arqextension.raster.Resize;
import de.hsmainz.cs.semgis.arqextension.raster.Reskew;
import de.hsmainz.cs.semgis.arqextension.raster.ScaleX;
import de.hsmainz.cs.semgis.arqextension.raster.ScaleY;
import de.hsmainz.cs.semgis.arqextension.raster.SkewX;
import de.hsmainz.cs.semgis.arqextension.raster.SkewY;
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
import io.github.galbiston.geosparql_jena.implementation.datatype.GMLDatatype;

import org.apache.jena.sparql.function.FunctionRegistry;

public class PostGISConfig {

    private static Boolean IS_FUNCTIONS_REGISTERED = false;

    public static final void setup() {

        //Only register functions once.
        if (!IS_FUNCTIONS_REGISTERED) {
            FunctionRegistry functionRegistry = FunctionRegistry.get();

            //POSTGIS functionRegistry
            functionRegistry.put(PostGISGeo.st_3ddwithin.getURI(), DWithin3D.class);
            functionRegistry.put(PostGISGeo.st_3dclosestpoint.getURI(), ClosestPoint3D.class);
            functionRegistry.put(PostGISGeo.st_3dlongestLine.getURI(), LongestLine3D.class);
            functionRegistry.put(PostGISGeo.st_3dmaxDistance.getURI(), MaxDistance3D.class);
            functionRegistry.put(PostGISGeo.st_3dshortestline.getURI(), ShortestLine3D.class);
            functionRegistry.put(PostGISGeo.st_addband.getURI(), AddBand.class);
            functionRegistry.put(PostGISGeo.st_addpoint.getURI(), AddPoint.class);
            functionRegistry.put(PostGISGeo.st_asbinary.getURI(), AsBinary.class);
            functionRegistry.put(PostGISGeo.st_asencodedpolyline.getURI(), AsEncodedPolyline.class);
            functionRegistry.put(PostGISGeo.st_ashexewkb.getURI(), AsHEXEWKB.class);
            functionRegistry.put(PostGISGeo.st_askml.getURI(), AsKML.class);
            functionRegistry.put(PostGISGeo.st_asgml.getURI(), AsGML.class);
            functionRegistry.put(PostGISGeo.st_asgeobuf.getURI(), AsGeoBuf.class);
            functionRegistry.put(PostGISGeo.st_asgeojson.getURI(), AsGeoJSON.class);
            functionRegistry.put(PostGISGeo.st_astiff.getURI(), AsTIFF.class);
            functionRegistry.put(PostGISGeo.st_asjpg.getURI(), AsJPG.class);
            functionRegistry.put(PostGISGeo.st_aspng.getURI(), AsPNG.class);
            functionRegistry.put(PostGISGeo.st_asmvt.getURI(), AsMVT.class);
            functionRegistry.put(PostGISGeo.st_assvg.getURI(), AsSVG.class);
            functionRegistry.put(PostGISGeo.st_astext.getURI(), AsText.class);
            functionRegistry.put(PostGISGeo.st_area.getURI(), Area.class);
            functionRegistry.put(PostGISGeo.st_azimuth.getURI(), Azimuth.class);
            functionRegistry.put(PostGISGeo.st_band.getURI(), Band.class);
            functionRegistry.put(PostGISGeo.st_bandmetadata.getURI(), BandMetaData.class);
            functionRegistry.put(PostGISGeo.st_bandnodatavalue.getURI(), BandNoDataValue.class);
            functionRegistry.put(PostGISGeo.st_bandpixeltype.getURI(), BandPixelType.class);
            functionRegistry.put(PostGISGeo.st_boundary.getURI(), Boundary.class);
            functionRegistry.put(PostGISGeo.st_boundingdiagonal.getURI(), BoundingDiagonal.class);
            functionRegistry.put(PostGISGeo.st_centroid.getURI(), Centroid.class);
            functionRegistry.put(PostGISGeo.st_chaikinSmooting.getURI(), ChaikinSmoothing.class);
            functionRegistry.put(PostGISGeo.st_clip.getURI(), Clip.class);
            functionRegistry.put(PostGISGeo.st_closestPoint.getURI(), ClosestPoint.class);
            functionRegistry.put(PostGISGeo.st_closestPoint3d.getURI(), ClosestPoint3D.class);
            functionRegistry.put(PostGISGeo.st_clusterIntersecting.getURI(), ClusterIntersecting.class);
            functionRegistry.put(PostGISGeo.st_clusterKMeans.getURI(), ClusterKMeans.class);
            functionRegistry.put(PostGISGeo.st_clusterWithin.getURI(), ClusterWithin.class);
            functionRegistry.put(PostGISGeo.st_collectionExtract.getURI(), CollectionExtract.class);
            functionRegistry.put(PostGISGeo.st_collectionHomogenize.getURI(), CollectionHomogenize.class);
            functionRegistry.put(PostGISGeo.st_concaveHull.getURI(), ConcaveHull.class);
            functionRegistry.put(PostGISGeo.st_count.getURI(), Count.class);
            functionRegistry.put(PostGISGeo.st_delaunayTriangles.getURI(), DelaunayTriangles.class);
            functionRegistry.put(PostGISGeo.st_dimension.getURI(), Dimension.class);
            functionRegistry.put(PostGISGeo.st_distance3d.getURI(), Distance3D.class);
            functionRegistry.put(PostGISGeo.st_distancesphere.getURI(), DistanceSphere.class);
            functionRegistry.put(PostGISGeo.st_endPoint.getURI(), EndPoint.class);
            functionRegistry.put(PostGISGeo.st_filterByM.getURI(), FilterByM.class);
            functionRegistry.put(PostGISGeo.st_flipCoordinates.getURI(), FlipCoordinates.class);
            functionRegistry.put(PostGISGeo.st_force2d.getURI(), Force2D.class);
            functionRegistry.put(PostGISGeo.st_force3d.getURI(), Force3D.class);
            functionRegistry.put(PostGISGeo.st_force3dm.getURI(), Force3DM.class);
            functionRegistry.put(PostGISGeo.st_force3dz.getURI(), Force3D.class);
            functionRegistry.put(PostGISGeo.st_force4d.getURI(), Force4D.class);
            functionRegistry.put(PostGISGeo.st_forceCollection.getURI(), ForceCollection.class);
            functionRegistry.put(PostGISGeo.st_geomFromGeoJSON.getURI(), GeomFromGeoJSON.class);
            functionRegistry.put(PostGISGeo.st_geomFromGML.getURI(), GeomFromGML.class);
            functionRegistry.put(PostGISGeo.st_geomFromKML.getURI(), GeomFromKML.class);
            functionRegistry.put(PostGISGeo.st_geomFromWKB.getURI(), GeomFromWKB.class);
            functionRegistry.put(PostGISGeo.st_geomFromText.getURI(), GeomFromText.class);
            functionRegistry.put(PostGISGeo.st_geometricMedian.getURI(), GeometricMedian.class);
            functionRegistry.put(PostGISGeo.st_geometryN.getURI(), GeometryN.class);
            functionRegistry.put(PostGISGeo.st_geometryType.getURI(), GeometryType.class);
            functionRegistry.put(PostGISGeo.st_gmlToSQL.getURI(), GeomFromGML.class);
            functionRegistry.put(PostGISGeo.st_hasNoBand.getURI(), HasNoBand.class);
            functionRegistry.put(PostGISGeo.st_hasRepeatedPoints.getURI(), HasRepeatedPoints.class);
            functionRegistry.put(PostGISGeo.st_height.getURI(), Height.class);
            functionRegistry.put(PostGISGeo.st_hausdorffDistance.getURI(), HausdorffDistance.class);
            functionRegistry.put(PostGISGeo.st_interiorRingN.getURI(), InteriorRingN.class);
            functionRegistry.put(PostGISGeo.st_isCollection.getURI(), IsCollection.class);
            functionRegistry.put(PostGISGeo.st_isClosed.getURI(), IsClosed.class);
            functionRegistry.put(PostGISGeo.st_isConvex.getURI(), IsConvex.class);
            functionRegistry.put(PostGISGeo.st_isEmpty.getURI(), IsEmptyFF.class);
            functionRegistry.put(PostGISGeo.st_isPolygonCW.getURI(), IsPolygonCW.class);
            functionRegistry.put(PostGISGeo.st_isPolygonCCW.getURI(), IsPolygonCCW.class);
            functionRegistry.put(PostGISGeo.st_isRing.getURI(), IsRing.class);
            functionRegistry.put(PostGISGeo.st_isSimple.getURI(), IsSimpleFF.class);
            functionRegistry.put(PostGISGeo.st_isValid.getURI(), IsValidFF.class);
            functionRegistry.put(PostGISGeo.st_isValidTrajectory.getURI(), IsValidTrajectory.class);
            functionRegistry.put(PostGISGeo.st_isValidReason.getURI(), IsValidReason.class);
            functionRegistry.put(PostGISGeo.st_Length.getURI(), Length.class);
            functionRegistry.put(PostGISGeo.st_Length2D.getURI(), Length.class);
            functionRegistry.put(PostGISGeo.st_lineCrossingDirection.getURI(), LineCrossingDirection.class);
            functionRegistry.put(PostGISGeo.st_lineFromEncodedPolyline.getURI(), LineFromEncodedPolyline.class);
            functionRegistry.put(PostGISGeo.st_lineFromMultiPoint.getURI(), LineFromMultiPoint.class);
            functionRegistry.put(PostGISGeo.st_lineFromWKB.getURI(), LineFromWKB.class);
            functionRegistry.put(PostGISGeo.st_lineFromText.getURI(), LineFromText.class);
            functionRegistry.put(PostGISGeo.st_lineMerge.getURI(), LineMerge.class);
            functionRegistry.put(PostGISGeo.st_lineSubstring.getURI(), LineSubstring.class);
            functionRegistry.put(PostGISGeo.st_locateAlong.getURI(), LocateAlong.class);
            functionRegistry.put(PostGISGeo.st_locateBetween.getURI(), LocateBetween.class);
            functionRegistry.put(PostGISGeo.st_locateBetweenElevations.getURI(), LocateBetweenElevations.class);
            functionRegistry.put(PostGISGeo.st_longestLine.getURI(), LongestLine.class);
            functionRegistry.put(PostGISGeo.st_m.getURI(), M.class);
            functionRegistry.put(PostGISGeo.st_maxDistance.getURI(), MaxDistance.class);
            functionRegistry.put(PostGISGeo.st_makeEmptyCoverage.getURI(), MakeEmptyCoverage.class);
            functionRegistry.put(PostGISGeo.st_makeEmptyRaster.getURI(), MakeEmptyRaster.class);
            functionRegistry.put(PostGISGeo.st_makeEnvelope.getURI(), MakeEnvelope.class);
            functionRegistry.put(PostGISGeo.st_makeLine.getURI(), MakeLine.class);
            functionRegistry.put(PostGISGeo.st_makePoint.getURI(), MakePoint.class);
            functionRegistry.put(PostGISGeo.st_makePointM.getURI(), MakePointM.class);
            functionRegistry.put(PostGISGeo.st_makePolygon.getURI(), MakePolygon.class);
            functionRegistry.put(PostGISGeo.st_memsize.getURI(), MemSize.class);
            functionRegistry.put(PostGISGeo.st_minimumBoundingCircle.getURI(), MinimumBoundingCircle.class);
            functionRegistry.put(PostGISGeo.st_minimumBoundingRadius.getURI(), MinimumBoundingRadius.class);
            functionRegistry.put(PostGISGeo.st_minimumClearance.getURI(), MinimumClearance.class);
            functionRegistry.put(PostGISGeo.st_minimumClearanceLine.getURI(), MinimumClearanceLine.class);
            functionRegistry.put(PostGISGeo.st_minConvexHull.getURI(), MinConvexHull.class);
            functionRegistry.put(PostGISGeo.st_mLineFromText.getURI(), MLineFromText.class);
            functionRegistry.put(PostGISGeo.st_mPointFromText.getURI(), MPointFromText.class);
            functionRegistry.put(PostGISGeo.st_mPolyFromText.getURI(), MPolyFromText.class);
            functionRegistry.put(PostGISGeo.st_multi.getURI(), Multi.class);
            functionRegistry.put(PostGISGeo.st_nearestValue.getURI(), NearestValue.class);
            functionRegistry.put(PostGISGeo.st_numBands.getURI(), NumBands.class);
            functionRegistry.put(PostGISGeo.st_numGeometries.getURI(), NumGeometries.class);
            functionRegistry.put(PostGISGeo.st_numInteriorRings.getURI(), NumInteriorRings.class);
            functionRegistry.put(PostGISGeo.st_numPoints.getURI(), NumPoints.class);
            functionRegistry.put(PostGISGeo.st_nPoints.getURI(), NumPoints.class);
            functionRegistry.put(PostGISGeo.st_nRings.getURI(), NRings.class);
            functionRegistry.put(PostGISGeo.st_normalize.getURI(), Normalize.class);
            functionRegistry.put(PostGISGeo.st_offsetCurve.getURI(), OffsetCurve.class);
            functionRegistry.put(PostGISGeo.st_orientation.getURI(), Orientation.class);
            functionRegistry.put(PostGISGeo.st_orderingEquals.getURI(), OrderingEquals.class);
            functionRegistry.put(PostGISGeo.st_patchN.getURI(), PatchN.class);
            functionRegistry.put(PostGISGeo.st_perimeter.getURI(), Perimeter.class);
            functionRegistry.put(PostGISGeo.st_perimeter2D.getURI(), Perimeter.class);
            functionRegistry.put(PostGISGeo.st_pixelAsPoint.getURI(), PixelAsPoint.class);
            functionRegistry.put(PostGISGeo.st_pixelAsPolygon.getURI(), PixelAsPolygon.class);
            functionRegistry.put(PostGISGeo.st_pixelHeight.getURI(), PixelHeight.class);
            functionRegistry.put(PostGISGeo.st_pixelWidth.getURI(), PixelWidth.class);
            functionRegistry.put(PostGISGeo.st_pointN.getURI(), PointN.class);
            functionRegistry.put(PostGISGeo.st_pointFromGeoHash.getURI(), PointFromGeoHash.class);
            functionRegistry.put(PostGISGeo.st_pointFromWKB.getURI(), PointFromWKB.class);
            functionRegistry.put(PostGISGeo.st_pointFromText.getURI(), PointFromText.class);
            functionRegistry.put(PostGISGeo.st_pointInsideCircle.getURI(), PointInsideCircle.class);
            functionRegistry.put(PostGISGeo.st_pointOnSurface.getURI(), PointOnSurface.class);
            functionRegistry.put(PostGISGeo.st_rastFromWKB.getURI(), RastFromWKB.class);
            functionRegistry.put(PostGISGeo.st_rastFromHexWKB.getURI(), RastFromHexWKB.class);
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
            functionRegistry.put(PostGISGeo.st_removeRepeatedPoints.getURI(), RemoveRepeatedPoints.class);
            functionRegistry.put(PostGISGeo.st_reskew.getURI(), Reskew.class);
            functionRegistry.put(PostGISGeo.st_resize.getURI(), Resize.class);
            functionRegistry.put(PostGISGeo.st_reverse.getURI(), Reverse.class);
            functionRegistry.put(PostGISGeo.st_rotate.getURI(), Rotate.class);
            functionRegistry.put(PostGISGeo.st_rotateX.getURI(), RotateX.class);
            functionRegistry.put(PostGISGeo.st_rotateY.getURI(), RotateY.class);
            functionRegistry.put(PostGISGeo.st_rotateZ.getURI(), RotateZ.class);
            functionRegistry.put(PostGISGeo.st_scale.getURI(), Scale.class);
            functionRegistry.put(PostGISGeo.st_scaleX.getURI(), ScaleX.class);
            functionRegistry.put(PostGISGeo.st_scaleY.getURI(), ScaleY.class);
            functionRegistry.put(PostGISGeo.st_segmentize.getURI(), Segmentize.class);
            functionRegistry.put(PostGISGeo.st_setPoint.getURI(), SetPoint.class);
            functionRegistry.put(PostGISGeo.st_simplify.getURI(), Simplify.class);
            functionRegistry.put(PostGISGeo.st_simplifyPreserveTopology.getURI(), SimplifyPreserveTopology.class);
            functionRegistry.put(PostGISGeo.st_simplifyVW.getURI(), SimplifyVW.class);
            functionRegistry.put(PostGISGeo.st_skewX.getURI(), SkewX.class);
            functionRegistry.put(PostGISGeo.st_skewY.getURI(), SkewY.class);
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
            functionRegistry.put(PostGISGeo.st_voronoiLines.getURI(), VoronoiLines.class);
            functionRegistry.put(PostGISGeo.st_voronoiPolygons.getURI(), VoronoiPolygons.class);
            functionRegistry.put(PostGISGeo.st_width.getURI(), Width.class);
            functionRegistry.put(PostGISGeo.st_wkbToSQL.getURI(), GeomFromWKB.class);
            functionRegistry.put(PostGISGeo.st_wktToSQL.getURI(), GeomFromText.class);
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
            functionRegistry.put(PostGISGeo.st_zMin.getURI(), ZMin.class);
            functionRegistry.put(PostGISGeo.st_zMax.getURI(), ZMax.class);
            functionRegistry.put(PostGISGeo.st_zmFlag.getURI(), Zmflag.class);
            // extra utility functionRegistry
            functionRegistry.put(Constants.SPATIAL_FUNCTION_NS + "transform", Transform.class);
            //functionRegistry.put(Constants.SPATIAL_FUNCTION_NS + "makeWKTPoint", CreateWKTPoint.class);
            //functionRegistry.put(Constants.SPATIAL_FUNCTION_NS + "WKTToGeometryPoint", LiteralToGeometryType.class);
            System.out.println(functionRegistry);
            GeoSPARQLConfig.setupMemoryIndex();
            IS_FUNCTIONS_REGISTERED = true;
        }
    }
    public static void main(String[] args) {
    	setup();
    }
}
