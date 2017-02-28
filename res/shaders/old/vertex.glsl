#version 400 core
in vec3 position;
out vec3 out_Colour;

void main(void){
    gl_Position = vec4(position, 1);
    out_Colour = vec3(position);
}
