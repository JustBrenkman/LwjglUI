#version 330

layout(location = 0) in vec3 position;
layout(location = 1) in vec2 textureCoord;
layout(location = 2) in vec3 normal;
layout(location = 3) in vec3 tangent;

out vec2 textureCoord0;

void main() {
    gl_Position = vec4(position, 1.0);
    textureCoord0 = textureCoord;
}