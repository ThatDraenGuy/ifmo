`timescale 1ns / 1ps

module func(
    input clk_i,    //сигнал такта
    input rst_i,    //сигнал сброса
    
    input [7:0] a_bi,
    input [7:0] b_bi,
    input start_i,  //сигнал начала вычисления
    
    output busy_o,  //сигнал занятости
    output reg [11:0] y_bo    
);

localparam IDLE = 2'b00;
localparam SQRT_B = 2'b01;
localparam MUL = 2'b10;

reg [1:0] state = IDLE;
reg [7:0] a;
reg [7:0] b;
reg [3:0] sqrt_b;
reg [11:0] y;

reg start_sqrt = 0;
reg start_mul = 0;
wire busy_sqrt;
wire busy_mul;
wire [3:0] sqrt_res;
wire [11:0] mul_res;

sqrt sqrt(
    .clk_i(clk_i),
    .rst_i(rst_i),
    .x_bi(b),
    .start_i(start_sqrt),
    
    .busy_o(busy_sqrt),
    .y_bo(sqrt_res)
);

mul mul(
    .clk_i(clk_i),
    .rst_i(rst_i),
    .a_bi(a),
    .b_bi(sqrt_b),
    .start_i(start_mul),
    
    .busy_o(busy_mul),
    .y_bo(mul_res)
);

assign busy_o = state != IDLE;

always @(posedge clk_i) begin
    if (rst_i) begin
        y_bo <= 0;
        state <= IDLE;
        start_sqrt <= 0;
        start_mul <= 0;
    end else if (start_sqrt != 0) begin
        start_sqrt <= 0;
    end else if (start_mul != 0) begin
        start_mul <= 0;
    end else begin
        case (state)
            IDLE:
                if (start_i) begin
                    state <= SQRT_B;
                    
                    a <= a_bi;
                    b <= b_bi;
                    start_sqrt <= 1;
                end
            SQRT_B:
                if (!busy_sqrt) begin
                    sqrt_b <= sqrt_res;
                    start_mul <= 1;
                    start_sqrt <= 0;
                    state <= MUL;
                end
            MUL:
                if (!busy_mul) begin
                    start_mul <= 0;
                    state <= IDLE;
                    y_bo <= mul_res;
                end
        endcase
    end
end


endmodule
