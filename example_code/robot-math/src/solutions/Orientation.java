public static FieldVector toRobotRelative(double fieldForward, double fieldStrafe, double headingRadians) {
      double cos = Math.cos(headingRadians);
      double sin = Math.sin(headingRadians);

      double robotForward = fieldForward * cos + fieldStrafe * sin;
      double robotStrafe = -fieldForward * sin + fieldStrafe * cos;

      return new FieldVector(robotForward, robotStrafe);
}