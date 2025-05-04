
attribute vec4 vPosition;
uniform mat4 MVPMatrix;

void main() {
    gl_Position = MVPMatrix * vPosition;
}
