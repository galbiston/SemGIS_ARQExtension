/*******************************************************************************
 * Copyright (c) 2017 Timo Homburg, i3Mainz.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the BSD License
 * which accompanies this distribution, and is available at
 * https://directory.fsf.org/wiki/License:BSD_4Clause
 *
 * This project extends work by Ian Simmons who developed the Parliament Triple Store.
 * http://parliament.semwebcentral.org and published his work und BSD License as well.
 *
 *     
 *******************************************************************************/
package de.hsmainz.cs.semgis.arqextension.vocabulary;

import org.apache.jena.graph.Node;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;

public class PostGISGeo {
   public static final String uri = "http://www.opengis.net/ont/geosparql#";



   protected static final Resource resource(String local) {
      return ResourceFactory.createResource(uri + local);
   }

   protected static final Property property(String local) {
      return ResourceFactory.createProperty(uri, local);
   }

   public static final Resource SpatialObject = resource("SpatialObject");
   public static final Resource Feature = resource("Feature");

   // simple features topological relations
   public static final Property st_addpoint = property("ST_AddPoint");
   public static final Property st_area = property("ST_Area");
   public static final Property st_asbinary = property("ST_AsBinary");
   public static final Property st_askml = property("ST_AsKML");
   public static final Property st_asgml = property("ST_AsGML");
   public static final Property st_asgeojson = property("ST_AsGeoJSON");
   public static final Property st_ashexewkb = property("ST_AsHexEWKB");
   public static final Property st_assvg = property("ST_AsSVG");
   public static final Property st_astext = property("ST_AsText");
   public static final Property st_azimuth = property("ST_Azimuth");
   public static final Property st_band = property("ST_Band");
   public static final Property st_bandmetadata = property("ST_BandMetaData");
   public static final Property st_bandnodatavalue = property("ST_BandNoDataValue");
   public static final Property st_bandpixeltype = property("ST_BandPixelType");
   public static final Property st_bboxintersect = property("ST_BBOXIntersect");
   public static final Property st_boundingdiagonal = property("ST_BoundingDiagonal");
   public static final Property st_centroid = property("ST_Centroid");
   public static final Property st_clip = property("ST_Clip");
   public static final Property st_closestPoint = property("ST_ClosestPoint");
   public static final Property st_concaveHull = property("ST_ConcaveHull");
   public static final Property st_delaunayTriangles = property("ST_DelaunayTriangles");
   public static final Property st_dimension = property("ST_Dimension");
   public static final Property st_distance3d = property("ST_3DDistance");
   public static final Property st_endPoint = property("ST_endPoint");
   public static final Property st_envelope = property("ST_Envelope");
   public static final Property st_flipCoordinates = property("ST_FlipCoordinates");
   public static final Property st_geomFromGeoJSON = property("ST_GeomFromGeoJSON");
   public static final Property st_geomFromWKB = property("ST_GeomFromWKB");
   public static final Property st_geomFromText = property("ST_GeomFromText");
   public static final Property st_geometryN = property("ST_GeometryN");
   public static final Property st_geometryType = property("ST_GeometryType");
   public static final Property st_hasNoBand = property("ST_HasNoBand");
   public static final Property st_hausdorffDistance = property("ST_HausdorffDistance");
   public static final Property st_height = property("ST_Height");
   public static final Property st_interiorRingN = property("ST_InteriorRingN");
   public static final Property st_isClosed = property("ST_IsClosed");
   public static final Property st_isCollection = property("ST_IsCollection");
   public static final Property st_isConvex = property("ST_IsConvex");
   public static final Property st_isEmpty = property("ST_IsEmpty");
   public static final Property st_isRing = property("ST_IsRing");
   public static final Property st_isSimple = property("ST_IsSimple");
   public static final Property st_isValid = property("ST_IsValid");
   public static final Property st_isValidReason = property("ST_IsValidReason");
   public static final Property st_Length = property("ST_Length");
   public static final Property st_Length2D = property("ST_Length2D");
   public static final Property st_lineFromWKB = property("ST_LineFromWKB");
   public static final Property st_lineFromText = property("ST_LineFromText");
   public static final Property st_m = property("ST_M");
   public static final Property st_makeEnvelope = property("ST_MakeEnvelope");
   public static final Property st_makeLine = property("ST_MakeLine");
   public static final Property st_makePoint = property("ST_MakePoint");
   public static final Property st_makePointM = property("ST_MakePointM");
   public static final Property st_makePolygon = property("ST_MakePolygon");
   public static final Property st_memsize = property("ST_MemSize");
   public static final Property st_minimumBoundingCircle = property("ST_MinimumBoundingCircle");
   public static final Property st_minimumBoundingRadius = property("ST_MinimumBoundingRadius");
   public static final Property st_minimumClearance = property("ST_MinimumClearance");
   public static final Property st_minimumClearanceLine = property("ST_MinimumClearanceLine");
   public static final Property st_nearestValue = property("ST_NearestValue");
   public static final Property st_normalize = property("ST_Normalize");
   public static final Property st_nRings = property("ST_NRings");
   public static final Property st_numBands = property("ST_NumBands");
   public static final Property st_numGeometries = property("ST_NumGeometries");
   public static final Property st_numInteriorRings = property("ST_NumInteriorRings");
   public static final Property st_numPoints = property("ST_NumPoints");
   public static final Property st_nPoints = property("ST_NPoints");
   public static final Property st_offsetCurve = property("ST_OffsetCurve");
   public static final Property st_orderingEquals = property("ST_OrderingEquals");
   public static final Property st_pixelAsPoint = property("ST_PixelAsPoint");
   public static final Property st_pixelHeight = property("ST_PixelHeight");
   public static final Property st_perimeter = property("ST_Perimeter");
   public static final Property st_perimeter2D = property("ST_Perimeter2D");
   public static final Property st_pointFromWKB = property("ST_PointFromWKB");
   public static final Property st_pointInsideCircle = property("ST_PointInsideCircle");
   public static final Property st_pointN = property("ST_PointN");
   public static final Property st_points = property("ST_Points");
   public static final Property st_pointFromGeoHash = property("ST_PointFromGeoHash");
   public static final Property st_pointFromText = property("ST_PointFromText");
   public static final Property st_polygonFromText = property("ST_PolygonFromText");
   public static final Property st_polygonFromWKB = property("ST_PolygonFromWKB");
   public static final Property st_rast_isEmpty = property("ST_rast_isEmpty");
   public static final Property st_rast_Contains = property("ST_rast_Contains");
   public static final Property st_rast_Covers = property("ST_rast_Covers");
   public static final Property st_rast_CoveredBy = property("ST_rast_CoveredBy");
   public static final Property st_rast_Disjoint = property("ST_rast_Disjoint");
   public static final Property st_rast_Intersects = property("ST_rast_Intersects");
   public static final Property st_rast_Overlaps = property("ST_rast_Overlaps");
   public static final Property st_rast_Touches = property("ST_rast_Touches");
   public static final Property st_rast_Within = property("ST_rast_Within");
   public static final Property st_rast_srid = property("ST_rast_SRID");
   public static final Property st_rasterToWorldCoord = property("ST_RasterToWorldCoord");
   public static final Property st_rasterToWorldCoordX = property("ST_RasterToWorldCoordX");
   public static final Property st_rasterToWorldCoordY = property("ST_RasterToWorldCoordY");
   public static final Property st_removePoint = property("ST_RemovePoint");
   public static final Property st_reverse = property("ST_Reverse");
   public static final Property st_rotate = property("ST_Rotate");
   public static final Property st_scale = property("ST_Scale");
   public static final Property st_segmentize = property("ST_Segmentize");
   public static final Property st_setPoint = property("ST_SetPoint");
   public static final Property st_simplify = property("ST_Simplify");
   public static final Property st_simplifyPreserveTopology = property("ST_SimplifyPreserveTopology");
   public static final Property st_simplifyVW = property("ST_SimplifyVW");
   public static final Property st_snap = property("ST_Snap");
   public static final Property st_split = property("ST_Split");
   public static final Property st_srid = property("ST_SRID");
   public static final Property st_startPoint = property("ST_StartPoint");
   public static final Property st_summary = property("ST_Summary");
   public static final Property st_summaryStats = property("ST_SummaryStats");
   public static final Property st_swapOrdinates = property("ST_SwapOrdinates");
   public static final Property st_transform = property("ST_Transform");
   public static final Property st_translate = property("ST_Translate");
   public static final Property st_transscale = property("ST_TransScale");
   public static final Property st_upperLeftX = property("ST_UpperLeftX");
   public static final Property st_upperLeftY = property("ST_UpperLeftY");
   public static final Property st_unaryUnion = property("ST_UnaryUnion");
   public static final Property st_value = property("ST_Value");
   public static final Property st_width = property("ST_Width");
   public static final Property st_worldToRasterCoord = property("ST_WorldToRasterCoord");
   public static final Property st_worldToRasterCoordX = property("ST_WorldToRasterCoordX");
   public static final Property st_worldToRasterCoordY = property("ST_WorldToRasterCoordY");
   public static final Property st_x = property("ST_X");
   public static final Property st_xMin = property("ST_XMin");
   public static final Property st_xMax = property("ST_XMax");
   public static final Property st_y = property("ST_Y");
   public static final Property st_yMin = property("ST_YMin");
   public static final Property st_yMax = property("ST_YMax");
   public static final Property st_z = property("ST_Z");
   public static final Property st_zMin = property("ST_ZMin");
   public static final Property st_zMax = property("ST_ZMax");
public static final String WKB = "WKB";
public static final String GeoJSON = "GeoJSON";
public static final String GeoBuf = "GeoBuf";
public static final String KML = "KML";
public static final String WKBRaster = "WKBRaster";
public static final String GEOTIFF = "GeoTIFF";










   public static class Nodes {
      public static final Node SpatialObject = PostGISGeo.SpatialObject.asNode();
      public static final Node Feature = PostGISGeo.Feature.asNode();
      // simple features topological relations
      public static final Node st_area= PostGISGeo.st_area.asNode();
      public static final Node st_azimuth= PostGISGeo.st_azimuth.asNode();
      public static final Node st_band= PostGISGeo.st_band.asNode();
      public static final Node st_bandmetadata= PostGISGeo.st_bandmetadata.asNode();
      public static final Node st_bandnodatavalue= PostGISGeo.st_bandnodatavalue.asNode();
      public static final Node st_bandpixeltype= PostGISGeo.st_bandpixeltype.asNode();
      public static final Node st_centroid = PostGISGeo.st_centroid.asNode();
      public static final Node st_clip = PostGISGeo.st_clip.asNode();
      public static final Node st_closestPoint = PostGISGeo.st_closestPoint.asNode();
      public static final Node st_concaveHull = PostGISGeo.st_concaveHull.asNode();
      public static final Node st_delaunayTriangles = PostGISGeo.st_delaunayTriangles.asNode();
      public static final Node st_dimension = PostGISGeo.st_dimension.asNode();
      public static final Node st_endPoint = PostGISGeo.st_endPoint.asNode();
      public static final Node st_envelope = PostGISGeo.st_envelope.asNode();
      public static final Node st_flipCoordinates = PostGISGeo.st_flipCoordinates.asNode();
      public static final Node st_geometryType = PostGISGeo.st_geometryType.asNode();
      public static final Node st_geometryN = PostGISGeo.st_geometryN.asNode();
      public static final Node st_hausdorffDistance = PostGISGeo.st_hausdorffDistance.asNode();
      public static final Node st_isClosed = PostGISGeo.st_isClosed.asNode();
      public static final Node st_isCollection = PostGISGeo.st_isCollection.asNode();
      public static final Node st_isEmpty = PostGISGeo.st_isEmpty.asNode();
      public static final Node st_isRing = PostGISGeo.st_isRing.asNode();
      public static final Node st_isSimple = PostGISGeo.st_isSimple.asNode();
      public static final Node st_isValid = PostGISGeo.st_isValid.asNode();
      public static final Node st_isValidReason = PostGISGeo.st_isValidReason.asNode();
      public static final Node st_Length = PostGISGeo.st_Length.asNode();
      public static final Node st_Length2D = PostGISGeo.st_Length2D.asNode();
      public static final Node st_m = PostGISGeo.st_m.asNode();
      public static final Node st_makeLine = PostGISGeo.st_makeLine.asNode();
      public static final Node st_makePoint = PostGISGeo.st_makePoint.asNode();
      public static final Node st_makePointM = PostGISGeo.st_makePointM.asNode();
      public static final Node st_makePolygon = PostGISGeo.st_makePolygon.asNode();
      public static final Node st_minimumBoundingCircle = PostGISGeo.st_minimumBoundingCircle.asNode();
      public static final Node st_nearestValue = PostGISGeo.st_nearestValue.asNode();
      public static final Node st_numBands = PostGISGeo.st_numBands.asNode();
      public static final Node st_numGeometries = PostGISGeo.st_numGeometries.asNode();
      public static final Node st_numPoints = PostGISGeo.st_numPoints.asNode();
      public static final Node st_nPoints = PostGISGeo.st_nPoints.asNode();  
      public static final Node st_offsetCurve = PostGISGeo.st_offsetCurve.asNode();
      public static final Node st_perimeter = PostGISGeo.st_perimeter.asNode();
      public static final Node st_perimeter2D = PostGISGeo.st_perimeter2D.asNode();
      public static final Node st_pixelAsPoint = PostGISGeo.st_pixelAsPoint.asNode();
      public static final Node st_pixelHeight = PostGISGeo.st_pixelHeight.asNode();
      public static final Node st_pointN = PostGISGeo.st_pointN.asNode();
      public static final Node st_rast_Contains = PostGISGeo.st_rast_Contains.asNode();
      public static final Node st_rast_Covers = PostGISGeo.st_rast_Covers.asNode();
      public static final Node st_rast_CoveredBy = PostGISGeo.st_rast_CoveredBy.asNode();
      public static final Node st_rast_Disjoint = PostGISGeo.st_rast_Disjoint.asNode();
      public static final Node st_rast_isEmpty = PostGISGeo.st_rast_isEmpty.asNode();
      public static final Node st_rast_Overlaps = PostGISGeo.st_rast_Overlaps.asNode();
      public static final Node st_rast_srid = PostGISGeo.st_rast_srid.asNode();
      public static final Node st_rast_Touches = PostGISGeo.st_rast_Touches.asNode();
      public static final Node st_rast_Within = PostGISGeo.st_rast_Within.asNode();
      public static final Node st_rasterToWorldCoord = PostGISGeo.st_rasterToWorldCoord.asNode();
      public static final Node st_rasterToWorldCoordX = PostGISGeo.st_rasterToWorldCoordX.asNode();
      public static final Node st_rasterToWorldCoordY = PostGISGeo.st_rasterToWorldCoordY.asNode();
      public static final Node st_reverse = PostGISGeo.st_reverse.asNode();
      public static final Node st_rotate = PostGISGeo.st_rotate.asNode();
      public static final Node st_scale = PostGISGeo.st_scale.asNode();
      public static final Node st_segmentize = PostGISGeo.st_segmentize.asNode();
      public static final Node st_setPoint = PostGISGeo.st_setPoint.asNode();
      public static final Node st_simplify = PostGISGeo.st_simplify.asNode();
      public static final Node st_simplifyPreserveTopology = PostGISGeo.st_simplifyPreserveTopology.asNode();
      public static final Node st_snap = PostGISGeo.st_snap.asNode();
      public static final Node st_split = PostGISGeo.st_split.asNode();
      public static final Node st_srid = PostGISGeo.st_srid.asNode();
      public static final Node st_startPoint = PostGISGeo.st_startPoint.asNode();
      public static final Node st_summary = PostGISGeo.st_summary.asNode();
      public static final Node st_summaryStats = PostGISGeo.st_summaryStats.asNode();
      public static final Node st_swapOrdinates = PostGISGeo.st_swapOrdinates.asNode();
      public static final Node st_transform = PostGISGeo.st_transform.asNode();
      public static final Node st_translate = PostGISGeo.st_translate.asNode();
      public static final Node st_transscale = PostGISGeo.st_transscale.asNode();
      public static final Node st_unaryUnion = PostGISGeo.st_unaryUnion.asNode();
      public static final Node st_upperLeftX = PostGISGeo.st_upperLeftX.asNode();
      public static final Node st_upperLeftY = PostGISGeo.st_upperLeftY.asNode();
      public static final Node st_value = PostGISGeo.st_value.asNode();
      public static final Node st_width = PostGISGeo.st_width.asNode();
      public static final Node st_worldToRasterCoord = PostGISGeo.st_worldToRasterCoord.asNode();
      public static final Node st_worldToRasterCoordX = PostGISGeo.st_worldToRasterCoordX.asNode();
      public static final Node st_worldToRasterCoordY = PostGISGeo.st_worldToRasterCoordY.asNode();
      public static final Node st_x = PostGISGeo.st_x.asNode();
      public static final Node st_xMin = PostGISGeo.st_xMin.asNode();
      public static final Node st_xMax = PostGISGeo.st_xMax.asNode();
      public static final Node st_y = PostGISGeo.st_y.asNode();
      public static final Node st_yMin = PostGISGeo.st_yMin.asNode();
      public static final Node st_yMax = PostGISGeo.st_yMax.asNode();
      public static final Node st_z = PostGISGeo.st_z.asNode();

   }
}
