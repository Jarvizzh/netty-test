// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: user.proto

package com.jarvis.grpc.proto;

public final class User {
  private User() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_jarvis_grpc_MyReq_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_jarvis_grpc_MyReq_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_jarvis_grpc_MyResp_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_jarvis_grpc_MyResp_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\nuser.proto\022\017com.jarvis.grpc\"\031\n\005MyReq\022\020" +
      "\n\010username\030\001 \001(\t\"\032\n\006MyResp\022\020\n\010realName\030\001" +
      " \001(\t2O\n\013UserService\022@\n\013getRealName\022\026.com" +
      ".jarvis.grpc.MyReq\032\027.com.jarvis.grpc.MyR" +
      "esp\"\000B\037\n\025com.jarvis.grpc.protoB\004UserP\001b\006" +
      "proto3"
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
    internal_static_com_jarvis_grpc_MyReq_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_jarvis_grpc_MyReq_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_jarvis_grpc_MyReq_descriptor,
        new String[] { "Username", });
    internal_static_com_jarvis_grpc_MyResp_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_jarvis_grpc_MyResp_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_jarvis_grpc_MyResp_descriptor,
        new String[] { "RealName", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
