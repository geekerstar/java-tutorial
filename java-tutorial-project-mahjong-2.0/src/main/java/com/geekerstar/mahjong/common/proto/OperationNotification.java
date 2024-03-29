// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Mahjong.proto

package com.geekerstar.mahjong.common.proto;

/**
 * Protobuf type {@code OperationNotification}
 */
public  final class OperationNotification extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:OperationNotification)
    OperationNotificationOrBuilder {
  // Use OperationNotification.newBuilder() to construct.
  private OperationNotification(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private OperationNotification() {
    operation_ = 0;
    pos_ = 0;
    fireCard_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private OperationNotification(
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
          case 16: {

            pos_ = input.readInt32();
            break;
          }
          case 24: {

            fireCard_ = input.readInt32();
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
    return com.geekerstar.mahjong.common.proto.Mahjong.internal_static_OperationNotification_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.geekerstar.mahjong.common.proto.Mahjong.internal_static_OperationNotification_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.geekerstar.mahjong.common.proto.OperationNotification.class, com.geekerstar.mahjong.common.proto.OperationNotification.Builder.class);
  }

  public static final int OPERATION_FIELD_NUMBER = 1;
  private int operation_;
  /**
   * <code>optional int32 operation = 1;</code>
   */
  public int getOperation() {
    return operation_;
  }

  public static final int POS_FIELD_NUMBER = 2;
  private int pos_;
  /**
   * <code>optional int32 pos = 2;</code>
   */
  public int getPos() {
    return pos_;
  }

  public static final int FIRECARD_FIELD_NUMBER = 3;
  private int fireCard_;
  /**
   * <code>optional int32 fireCard = 3;</code>
   */
  public int getFireCard() {
    return fireCard_;
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
    if (pos_ != 0) {
      output.writeInt32(2, pos_);
    }
    if (fireCard_ != 0) {
      output.writeInt32(3, fireCard_);
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
    if (pos_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, pos_);
    }
    if (fireCard_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, fireCard_);
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
    if (!(obj instanceof com.geekerstar.mahjong.common.proto.OperationNotification)) {
      return super.equals(obj);
    }
    com.geekerstar.mahjong.common.proto.OperationNotification other = (com.geekerstar.mahjong.common.proto.OperationNotification) obj;

    boolean result = true;
    result = result && (getOperation()
        == other.getOperation());
    result = result && (getPos()
        == other.getPos());
    result = result && (getFireCard()
        == other.getFireCard());
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
    hash = (37 * hash) + POS_FIELD_NUMBER;
    hash = (53 * hash) + getPos();
    hash = (37 * hash) + FIRECARD_FIELD_NUMBER;
    hash = (53 * hash) + getFireCard();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.geekerstar.mahjong.common.proto.OperationNotification parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.geekerstar.mahjong.common.proto.OperationNotification parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.geekerstar.mahjong.common.proto.OperationNotification parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.geekerstar.mahjong.common.proto.OperationNotification parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.geekerstar.mahjong.common.proto.OperationNotification parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.geekerstar.mahjong.common.proto.OperationNotification parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.geekerstar.mahjong.common.proto.OperationNotification parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.geekerstar.mahjong.common.proto.OperationNotification parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.geekerstar.mahjong.common.proto.OperationNotification parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.geekerstar.mahjong.common.proto.OperationNotification parseFrom(
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
  public static Builder newBuilder(com.geekerstar.mahjong.common.proto.OperationNotification prototype) {
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
   * Protobuf type {@code OperationNotification}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:OperationNotification)
      com.geekerstar.mahjong.common.proto.OperationNotificationOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.geekerstar.mahjong.common.proto.Mahjong.internal_static_OperationNotification_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.geekerstar.mahjong.common.proto.Mahjong.internal_static_OperationNotification_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.geekerstar.mahjong.common.proto.OperationNotification.class, com.geekerstar.mahjong.common.proto.OperationNotification.Builder.class);
    }

    // Construct using com.geekerstar.mahjong.common.proto.OperationNotification.newBuilder()
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

      pos_ = 0;

      fireCard_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.geekerstar.mahjong.common.proto.Mahjong.internal_static_OperationNotification_descriptor;
    }

    public com.geekerstar.mahjong.common.proto.OperationNotification getDefaultInstanceForType() {
      return com.geekerstar.mahjong.common.proto.OperationNotification.getDefaultInstance();
    }

    public com.geekerstar.mahjong.common.proto.OperationNotification build() {
      com.geekerstar.mahjong.common.proto.OperationNotification result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.geekerstar.mahjong.common.proto.OperationNotification buildPartial() {
      com.geekerstar.mahjong.common.proto.OperationNotification result = new com.geekerstar.mahjong.common.proto.OperationNotification(this);
      result.operation_ = operation_;
      result.pos_ = pos_;
      result.fireCard_ = fireCard_;
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
      if (other instanceof com.geekerstar.mahjong.common.proto.OperationNotification) {
        return mergeFrom((com.geekerstar.mahjong.common.proto.OperationNotification)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.geekerstar.mahjong.common.proto.OperationNotification other) {
      if (other == com.geekerstar.mahjong.common.proto.OperationNotification.getDefaultInstance()) return this;
      if (other.getOperation() != 0) {
        setOperation(other.getOperation());
      }
      if (other.getPos() != 0) {
        setPos(other.getPos());
      }
      if (other.getFireCard() != 0) {
        setFireCard(other.getFireCard());
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
      com.geekerstar.mahjong.common.proto.OperationNotification parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.geekerstar.mahjong.common.proto.OperationNotification) e.getUnfinishedMessage();
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

    private int pos_ ;
    /**
     * <code>optional int32 pos = 2;</code>
     */
    public int getPos() {
      return pos_;
    }
    /**
     * <code>optional int32 pos = 2;</code>
     */
    public Builder setPos(int value) {

      pos_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int32 pos = 2;</code>
     */
    public Builder clearPos() {

      pos_ = 0;
      onChanged();
      return this;
    }

    private int fireCard_ ;
    /**
     * <code>optional int32 fireCard = 3;</code>
     */
    public int getFireCard() {
      return fireCard_;
    }
    /**
     * <code>optional int32 fireCard = 3;</code>
     */
    public Builder setFireCard(int value) {

      fireCard_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int32 fireCard = 3;</code>
     */
    public Builder clearFireCard() {

      fireCard_ = 0;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:OperationNotification)
  }

  // @@protoc_insertion_point(class_scope:OperationNotification)
  private static final com.geekerstar.mahjong.common.proto.OperationNotification DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.geekerstar.mahjong.common.proto.OperationNotification();
  }

  public static com.geekerstar.mahjong.common.proto.OperationNotification getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<OperationNotification>
      PARSER = new com.google.protobuf.AbstractParser<OperationNotification>() {
    public OperationNotification parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new OperationNotification(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<OperationNotification> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<OperationNotification> getParserForType() {
    return PARSER;
  }

  public com.geekerstar.mahjong.common.proto.OperationNotification getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

