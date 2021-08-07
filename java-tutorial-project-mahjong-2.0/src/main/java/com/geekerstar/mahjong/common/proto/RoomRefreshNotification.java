// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Mahjong.proto

package com.geekerstar.mahjong.common.proto;

/**
 * Protobuf type {@code RoomRefreshNotification}
 */
public  final class RoomRefreshNotification extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:RoomRefreshNotification)
    RoomRefreshNotificationOrBuilder {
  // Use RoomRefreshNotification.newBuilder() to construct.
  private RoomRefreshNotification(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RoomRefreshNotification() {
    operation_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private RoomRefreshNotification(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 8: {

            operation_ = input.readInt32();
            break;
          }
          case 18: {
            com.geekerstar.mahjong.common.proto.RoomMsg.Builder subBuilder = null;
            if (room_ != null) {
              subBuilder = room_.toBuilder();
            }
            room_ = input.readMessage(com.geekerstar.mahjong.common.proto.RoomMsg.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(room_);
              room_ = subBuilder.buildPartial();
            }

            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.geekerstar.mahjong.common.proto.Mahjong.internal_static_RoomRefreshNotification_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.geekerstar.mahjong.common.proto.Mahjong.internal_static_RoomRefreshNotification_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.geekerstar.mahjong.common.proto.RoomRefreshNotification.class, com.geekerstar.mahjong.common.proto.RoomRefreshNotification.Builder.class);
  }

  public static final int OPERATION_FIELD_NUMBER = 1;
  private int operation_;
  /**
   * <code>optional int32 operation = 1;</code>
   */
  public int getOperation() {
    return operation_;
  }

  public static final int ROOM_FIELD_NUMBER = 2;
  private com.geekerstar.mahjong.common.proto.RoomMsg room_;
  /**
   * <code>optional .RoomMsg room = 2;</code>
   */
  public boolean hasRoom() {
    return room_ != null;
  }
  /**
   * <code>optional .RoomMsg room = 2;</code>
   */
  public com.geekerstar.mahjong.common.proto.RoomMsg getRoom() {
    return room_ == null ? com.geekerstar.mahjong.common.proto.RoomMsg.getDefaultInstance() : room_;
  }
  /**
   * <code>optional .RoomMsg room = 2;</code>
   */
  public com.geekerstar.mahjong.common.proto.RoomMsgOrBuilder getRoomOrBuilder() {
    return getRoom();
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (operation_ != 0) {
      output.writeInt32(1, operation_);
    }
    if (room_ != null) {
      output.writeMessage(2, getRoom());
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (operation_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, operation_);
    }
    if (room_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getRoom());
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.geekerstar.mahjong.common.proto.RoomRefreshNotification)) {
      return super.equals(obj);
    }
    com.geekerstar.mahjong.common.proto.RoomRefreshNotification other = (com.geekerstar.mahjong.common.proto.RoomRefreshNotification) obj;

    boolean result = true;
    result = result && (getOperation()
        == other.getOperation());
    result = result && (hasRoom() == other.hasRoom());
    if (hasRoom()) {
      result = result && getRoom()
          .equals(other.getRoom());
    }
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    hash = (37 * hash) + OPERATION_FIELD_NUMBER;
    hash = (53 * hash) + getOperation();
    if (hasRoom()) {
      hash = (37 * hash) + ROOM_FIELD_NUMBER;
      hash = (53 * hash) + getRoom().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.geekerstar.mahjong.common.proto.RoomRefreshNotification parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.geekerstar.mahjong.common.proto.RoomRefreshNotification parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.geekerstar.mahjong.common.proto.RoomRefreshNotification parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.geekerstar.mahjong.common.proto.RoomRefreshNotification parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.geekerstar.mahjong.common.proto.RoomRefreshNotification parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.geekerstar.mahjong.common.proto.RoomRefreshNotification parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.geekerstar.mahjong.common.proto.RoomRefreshNotification parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.geekerstar.mahjong.common.proto.RoomRefreshNotification parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.geekerstar.mahjong.common.proto.RoomRefreshNotification parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.geekerstar.mahjong.common.proto.RoomRefreshNotification parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.geekerstar.mahjong.common.proto.RoomRefreshNotification prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code RoomRefreshNotification}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:RoomRefreshNotification)
      com.geekerstar.mahjong.common.proto.RoomRefreshNotificationOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.geekerstar.mahjong.common.proto.Mahjong.internal_static_RoomRefreshNotification_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.geekerstar.mahjong.common.proto.Mahjong.internal_static_RoomRefreshNotification_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.geekerstar.mahjong.common.proto.RoomRefreshNotification.class, com.geekerstar.mahjong.common.proto.RoomRefreshNotification.Builder.class);
    }

    // Construct using com.geekerstar.mahjong.common.proto.RoomRefreshNotification.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      operation_ = 0;

      if (roomBuilder_ == null) {
        room_ = null;
      } else {
        room_ = null;
        roomBuilder_ = null;
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.geekerstar.mahjong.common.proto.Mahjong.internal_static_RoomRefreshNotification_descriptor;
    }

    public com.geekerstar.mahjong.common.proto.RoomRefreshNotification getDefaultInstanceForType() {
      return com.geekerstar.mahjong.common.proto.RoomRefreshNotification.getDefaultInstance();
    }

    public com.geekerstar.mahjong.common.proto.RoomRefreshNotification build() {
      com.geekerstar.mahjong.common.proto.RoomRefreshNotification result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.geekerstar.mahjong.common.proto.RoomRefreshNotification buildPartial() {
      com.geekerstar.mahjong.common.proto.RoomRefreshNotification result = new com.geekerstar.mahjong.common.proto.RoomRefreshNotification(this);
      result.operation_ = operation_;
      if (roomBuilder_ == null) {
        result.room_ = room_;
      } else {
        result.room_ = roomBuilder_.build();
      }
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.geekerstar.mahjong.common.proto.RoomRefreshNotification) {
        return mergeFrom((com.geekerstar.mahjong.common.proto.RoomRefreshNotification)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.geekerstar.mahjong.common.proto.RoomRefreshNotification other) {
      if (other == com.geekerstar.mahjong.common.proto.RoomRefreshNotification.getDefaultInstance()) return this;
      if (other.getOperation() != 0) {
        setOperation(other.getOperation());
      }
      if (other.hasRoom()) {
        mergeRoom(other.getRoom());
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.geekerstar.mahjong.common.proto.RoomRefreshNotification parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.geekerstar.mahjong.common.proto.RoomRefreshNotification) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int operation_ ;
    /**
     * <code>optional int32 operation = 1;</code>
     */
    public int getOperation() {
      return operation_;
    }
    /**
     * <code>optional int32 operation = 1;</code>
     */
    public Builder setOperation(int value) {

      operation_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int32 operation = 1;</code>
     */
    public Builder clearOperation() {

      operation_ = 0;
      onChanged();
      return this;
    }

    private com.geekerstar.mahjong.common.proto.RoomMsg room_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.geekerstar.mahjong.common.proto.RoomMsg, com.geekerstar.mahjong.common.proto.RoomMsg.Builder, com.geekerstar.mahjong.common.proto.RoomMsgOrBuilder> roomBuilder_;
    /**
     * <code>optional .RoomMsg room = 2;</code>
     */
    public boolean hasRoom() {
      return roomBuilder_ != null || room_ != null;
    }
    /**
     * <code>optional .RoomMsg room = 2;</code>
     */
    public com.geekerstar.mahjong.common.proto.RoomMsg getRoom() {
      if (roomBuilder_ == null) {
        return room_ == null ? com.geekerstar.mahjong.common.proto.RoomMsg.getDefaultInstance() : room_;
      } else {
        return roomBuilder_.getMessage();
      }
    }
    /**
     * <code>optional .RoomMsg room = 2;</code>
     */
    public Builder setRoom(com.geekerstar.mahjong.common.proto.RoomMsg value) {
      if (roomBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        room_ = value;
        onChanged();
      } else {
        roomBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>optional .RoomMsg room = 2;</code>
     */
    public Builder setRoom(
        com.geekerstar.mahjong.common.proto.RoomMsg.Builder builderForValue) {
      if (roomBuilder_ == null) {
        room_ = builderForValue.build();
        onChanged();
      } else {
        roomBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>optional .RoomMsg room = 2;</code>
     */
    public Builder mergeRoom(com.geekerstar.mahjong.common.proto.RoomMsg value) {
      if (roomBuilder_ == null) {
        if (room_ != null) {
          room_ =
            com.geekerstar.mahjong.common.proto.RoomMsg.newBuilder(room_).mergeFrom(value).buildPartial();
        } else {
          room_ = value;
        }
        onChanged();
      } else {
        roomBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>optional .RoomMsg room = 2;</code>
     */
    public Builder clearRoom() {
      if (roomBuilder_ == null) {
        room_ = null;
        onChanged();
      } else {
        room_ = null;
        roomBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>optional .RoomMsg room = 2;</code>
     */
    public com.geekerstar.mahjong.common.proto.RoomMsg.Builder getRoomBuilder() {

      onChanged();
      return getRoomFieldBuilder().getBuilder();
    }
    /**
     * <code>optional .RoomMsg room = 2;</code>
     */
    public com.geekerstar.mahjong.common.proto.RoomMsgOrBuilder getRoomOrBuilder() {
      if (roomBuilder_ != null) {
        return roomBuilder_.getMessageOrBuilder();
      } else {
        return room_ == null ?
            com.geekerstar.mahjong.common.proto.RoomMsg.getDefaultInstance() : room_;
      }
    }
    /**
     * <code>optional .RoomMsg room = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.geekerstar.mahjong.common.proto.RoomMsg, com.geekerstar.mahjong.common.proto.RoomMsg.Builder, com.geekerstar.mahjong.common.proto.RoomMsgOrBuilder>
        getRoomFieldBuilder() {
      if (roomBuilder_ == null) {
        roomBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.geekerstar.mahjong.common.proto.RoomMsg, com.geekerstar.mahjong.common.proto.RoomMsg.Builder, com.geekerstar.mahjong.common.proto.RoomMsgOrBuilder>(
                getRoom(),
                getParentForChildren(),
                isClean());
        room_ = null;
      }
      return roomBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:RoomRefreshNotification)
  }

  // @@protoc_insertion_point(class_scope:RoomRefreshNotification)
  private static final com.geekerstar.mahjong.common.proto.RoomRefreshNotification DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.geekerstar.mahjong.common.proto.RoomRefreshNotification();
  }

  public static com.geekerstar.mahjong.common.proto.RoomRefreshNotification getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<RoomRefreshNotification>
      PARSER = new com.google.protobuf.AbstractParser<RoomRefreshNotification>() {
    public RoomRefreshNotification parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new RoomRefreshNotification(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<RoomRefreshNotification> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RoomRefreshNotification> getParserForType() {
    return PARSER;
  }

  public com.geekerstar.mahjong.common.proto.RoomRefreshNotification getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

