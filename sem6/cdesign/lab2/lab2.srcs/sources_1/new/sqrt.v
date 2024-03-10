`timescale 1ns / 1ps

module sqrt(
    input clk_i,    //сигнал такта
    input rst_i,    //сигнал сброса
    
    input [7:0] x_bi,
    input start_i,  //сигнал начала вычисления
    
    output busy_o,  //сигнал занятости
    output reg [3:0] y_bo    
);
    localparam IDLE = 1'b0;
    localparam WORK = 1'b1;
    
    reg [7:0] m;
    reg [7:0] y;
    reg [7:0] x;
    wire [7:0] b;
    wire end_step;
    wire [7:0] diff;
    reg state = IDLE;
    
    
    assign b = y|m;
    assign diff = x - b;
    assign end_step = m == 0;
    assign busy_o = state == WORK;
    
    
    always @(posedge clk_i)
        if (rst_i) begin
            m <= 0;
            y <= 0;
            y_bo <= 0;
            
            state <= IDLE;
        end else begin
            case (state)
                IDLE:
                    if (start_i) begin
                        state <= WORK;
                        
                        x <= x_bi;
                        m <= 1 << 6;
                        y <= 0;
                    end
                WORK:
                    begin
                        if (end_step) begin
                            state <= IDLE;
                            y_bo <= y;
                        end
                        
                        y <= y >> 1;
                        
                        if (diff <= x) begin
                            x <= diff;
                            y <= (y >> 1)|m;
                        end
                        
                        m <= m >> 2;
                    end
            endcase
        end

    
    
endmodule
