
attribute vec4 vPosition;
uniform mat4 VPMatrix;
uniform mat4 MMatrix;

void main() {
    gl_Position = VPMatrix * MMatrix * vPosition;
}
