// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Mahjong.proto

package com.geekerstar.mahjong.common.proto;

public final class Mahjong {
  private Mahjong() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_PlayerMsg_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_PlayerMsg_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_RoomMsg_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_RoomMsg_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CreateRoomRequest_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CreateRoomRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CreateRoomResponse_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CreateRoomResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_EnterRoomRequest_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_EnterRoomRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_EnterRoomResponse_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_EnterRoomResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_GameOverNotification_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_GameOverNotification_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_HelloRequest_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_HelloRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_HelloResponse_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_HelloResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_LoginRequest_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_LoginRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_LoginResponse_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_LoginResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_OperationNotification_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_OperationNotification_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_OperationRequest_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_OperationRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_OperationResultNotification_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_OperationResultNotification_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_RoomRefreshNotification_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_RoomRefreshNotification_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_SettlementNotification_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_SettlementNotification_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_StartGameMessage_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_StartGameMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rMahjong.proto\"\234\001\n\tPlayerMsg\022\n\n\002id\030\001 \001(" +
      "\003\022\020\n\010username\030\002 \001(\t\022\020\n\010password\030\003 \001(\t\022\r\n" +
      "\005score\030\004 \001(\005\022\013\n\003pos\030\005 \001(\005\022\r\n\005cards\030\006 \001(\014" +
      "\022\020\n\010chuCards\030\007 \001(\014\022\020\n\010pengList\030\010 \001(\014\022\020\n\010" +
      "gangList\030\t \001(\014\"\235\001\n\007RoomMsg\022\n\n\002id\030\001 \001(\003\022\024" +
      "\n\014maxPlayerNum\030\002 \001(\005\022\021\n\tbaseScore\030\003 \001(\005\022" +
      "\033\n\007players\030\004 \003(\0132\n.PlayerMsg\022\023\n\013remainCa" +
      "rds\030\005 \001(\014\022\016\n\006chuPos\030\006 \001(\005\022\033\n\006status\030\007 \001(" +
      "\0162\013.RoomStatus\"9\n\021CreateRoomRequest\022\021\n\tp" +
      "layerNum\030\001 \001(\005\022\021\n\tbaseScore\030\002 \001(\005\"5\n\022Cre",
      "ateRoomResponse\022\016\n\006result\030\001 \001(\010\022\017\n\007messa" +
      "ge\030\002 \001(\t\"\"\n\020EnterRoomRequest\022\016\n\006roomId\030\001" +
      " \001(\003\"4\n\021EnterRoomResponse\022\016\n\006result\030\001 \001(" +
      "\010\022\017\n\007message\030\002 \001(\t\".\n\024GameOverNotificati" +
      "on\022\026\n\004room\030\001 \001(\0132\010.RoomMsg\"\034\n\014HelloReque" +
      "st\022\014\n\004name\030\001 \001(\t\" \n\rHelloResponse\022\017\n\007mes" +
      "sage\030\001 \001(\t\"2\n\014LoginRequest\022\020\n\010username\030\001" +
      " \001(\t\022\020\n\010password\030\002 \001(\t\"L\n\rLoginResponse\022" +
      "\016\n\006result\030\001 \001(\010\022\032\n\006player\030\002 \001(\0132\n.Player" +
      "Msg\022\017\n\007message\030\003 \001(\t\"I\n\025OperationNotific",
      "ation\022\021\n\toperation\030\001 \001(\005\022\013\n\003pos\030\002 \001(\005\022\020\n" +
      "\010fireCard\030\003 \001(\005\"@\n\020OperationRequest\022\021\n\to" +
      "peration\030\001 \001(\005\022\013\n\003pos\030\002 \001(\005\022\014\n\004card\030\003 \001(" +
      "\005\"K\n\033OperationResultNotification\022\021\n\toper" +
      "ation\030\001 \001(\005\022\013\n\003pos\030\002 \001(\005\022\014\n\004card\030\003 \001(\005\"D" +
      "\n\027RoomRefreshNotification\022\021\n\toperation\030\001" +
      " \001(\005\022\026\n\004room\030\002 \001(\0132\010.RoomMsg\"(\n\026Settleme" +
      "ntNotification\022\016\n\006scores\030\001 \003(\005\"\022\n\020StartG" +
      "ameMessage*\215\001\n\nRoomStatus\022\031\n\025STATUS_WAIT" +
      "ING_PLAYER\020\000\022\030\n\024STATUS_GAME_STARTING\020\001\022\026",
      "\n\022STATUS_WAITING_CHU\020\002\022\034\n\030STATUS_WAITING" +
      "_OPERATION\020\003\022\024\n\020STATUS_GAME_OVER\020\004B(\n$co" +
      "m.imooc.netty.mahjong.common.protoP\001b\006pr" +
      "oto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_PlayerMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_PlayerMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_PlayerMsg_descriptor,
        new java.lang.String[] { "Id", "Username", "Password", "Score", "Pos", "Cards", "ChuCards", "PengList", "GangList", });
    internal_static_RoomMsg_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_RoomMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_RoomMsg_descriptor,
        new java.lang.String[] { "Id", "MaxPlayerNum", "BaseScore", "Players", "RemainCards", "ChuPos", "Status", });
    internal_static_CreateRoomRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_CreateRoomRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CreateRoomRequest_descriptor,
        new java.lang.String[] { "PlayerNum", "BaseScore", });
    internal_static_CreateRoomResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_CreateRoomResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CreateRoomResponse_descriptor,
        new java.lang.String[] { "Result", "Message", });
    internal_static_EnterRoomRequest_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_EnterRoomRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_EnterRoomRequest_descriptor,
        new java.lang.String[] { "RoomId", });
    internal_static_EnterRoomResponse_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_EnterRoomResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_EnterRoomResponse_descriptor,
        new java.lang.String[] { "Result", "Message", });
    internal_static_GameOverNotification_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_GameOverNotification_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_GameOverNotification_descriptor,
        new java.lang.String[] { "Room", });
    internal_static_HelloRequest_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_HelloRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_HelloRequest_descriptor,
        new java.lang.String[] { "Name", });
    internal_static_HelloResponse_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_HelloResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_HelloResponse_descriptor,
        new java.lang.String[] { "Message", });
    internal_static_LoginRequest_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_LoginRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_LoginRequest_descriptor,
        new java.lang.String[] { "Username", "Password", });
    internal_static_LoginResponse_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_LoginResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_LoginResponse_descriptor,
        new java.lang.String[] { "Result", "Player", "Message", });
    internal_static_OperationNotification_descriptor =
      getDescriptor().getMessageTypes().get(11);
    internal_static_OperationNotification_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_OperationNotification_descriptor,
        new java.lang.String[] { "Operation", "Pos", "FireCard", });
    internal_static_OperationRequest_descriptor =
      getDescriptor().getMessageTypes().get(12);
    internal_static_OperationRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_OperationRequest_descriptor,
        new java.lang.String[] { "Operation", "Pos", "Card", });
    internal_static_OperationResultNotification_descriptor =
      getDescriptor().getMessageTypes().get(13);
    internal_static_OperationResultNotification_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_OperationResultNotification_descriptor,
        new java.lang.String[] { "Operation", "Pos", "Card", });
    internal_static_RoomRefreshNotification_descriptor =
      getDescriptor().getMessageTypes().get(14);
    internal_static_RoomRefreshNotification_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_RoomRefreshNotification_descriptor,
        new java.lang.String[] { "Operation", "Room", });
    internal_static_SettlementNotification_descriptor =
      getDescriptor().getMessageTypes().get(15);
    internal_static_SettlementNotification_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_SettlementNotification_descriptor,
        new java.lang.String[] { "Scores", });
    internal_static_StartGameMessage_descriptor =
      getDescriptor().getMessageTypes().get(16);
    internal_static_StartGameMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_StartGameMessage_descriptor,
        new java.lang.String[] { });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
