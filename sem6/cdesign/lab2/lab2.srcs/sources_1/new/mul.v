`timescale 1ns / 1ps

module mul(
    input clk_i,    //сигнал такта
    input rst_i,    //сигнал сброса
    
    input [7:0] a_bi,
    input [3:0] b_bi,
    input start_i,  //сигнал начала вычисления
    
    output busy_o,  //сигнал занятости
    output reg [11:0] y_bo    
);

    localparam IDLE = 1'b0;
    localparam WORK = 1'b1;
    
    reg   [2:0] counter;
    wire  [2:0] end_step;
    wire  [7:0] partial_sum;
    wire [11:0] shifted_partial_sum;
    reg   [7:0] a; 
    reg   [3:0] b;
    reg  [11:0] partial_result;
    reg         state = IDLE;
    
    assign partial_sum = a & {8{b[counter]}}; // 0 if b[counter] == 0; a if b[counter] == 1
    assign shifted_partial_sum = partial_sum << counter;
    assign end_step = (counter == 4);
    assign busy_o = state;
    
    always @(posedge clk_i)
        if (rst_i) begin
            counter <= 0;
            partial_result <= 0;
            y_bo <= 0;
            
            state <= IDLE;
        end else begin
            case (state)
                IDLE:
                    if (start_i) begin
                        state <= WORK;
                        
                        a <= a_bi;
                        b <= b_bi;
                        counter <= 0;
                        partial_result <= 0;
                    end
                WORK:
                    begin
                        if (end_step) begin
                            state <= IDLE;
                            y_bo <= partial_result;
                        end
                        
                        partial_result <= partial_result + shifted_partial_sum;
                        counter <= counter + 1;
                    end
            endcase
        end


endmodule
